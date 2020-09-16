package DB;

import entities.Author;
import entities.Book;
import entities.BookGenre;
import entities.Catalog;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Stateless
@Local(BookDB.class)
public class BookDB  {
    @PersistenceContext(name = "book-persistence-unit")
    private EntityManager em;

    
    public List<Book> getAllBooks() {
        try {
            Query q = em.createQuery("FROM Book", Book.class);
            List<Book> books = q.getResultList();
            return books;
        }
        catch (Exception e){
            System.err.println("An error occurred during selecting entities: " + e);
        }
        return null;
    }

    
    public void addBook(String title, BigInteger isbn, String authorName, String authorSurname, String genre, int quantity) {
        try {
            Book book = new Book(title, isbn);

            // check if author exists in database
            String authorQueryStr = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname";
            Query authorQuery = em.createQuery(authorQueryStr);
            authorQuery.setParameter("name", authorName);
            authorQuery.setParameter("surname", authorSurname);
            List<Author> authors = authorQuery.getResultList();

            //check if book genre exists in database
            String genreQueryStr = "SELECT c FROM BookGenre c WHERE c.name = :name";
            Query genreQuery = em.createQuery(genreQueryStr);
            genreQuery.setParameter("name", genre);
            List<BookGenre> genres = genreQuery.getResultList();

            // if author does not exists then add new one, select existing one otherwise
            if ( authors.isEmpty() ){
                Author newAuthor = new Author(authorName, authorSurname);
                // update books field of author entity
                newAuthor.addBook(book);
                em.persist(newAuthor);
            } else {
                for (Author author : authors) {
                    author.addBook(book);
                }
            }

            // if genre does not exists then add new one, select existing one otherwise
            if( genres.isEmpty()){
                BookGenre newGenre = new BookGenre(genre);
                // update books field of genre entity
                newGenre.addBook(book);
                em.persist(newGenre);
            } else {
                for(BookGenre bookGenre : genres){
                    bookGenre.addBook(book);
                }
            }

            // catalog to book is one to one mapping so new catalog must be created
            Catalog newCatalog = new Catalog(quantity, quantity);
            newCatalog.setBook(book);
            book.setCatalog(newCatalog);
            em.persist(newCatalog);
            em.persist(book);

        } catch ( Exception e ) {
            System.err.println("An error occurred during addition of a book: " + e);
        }
    }

    
    public void updateBook(int id, String title, BigInteger isbn, String authorName, String authorSurname, String genre, int quantity) {
        try {

            Book updatedBook = em.find(Book.class, id);

            if (!updatedBook.getTitle().equals(title)) {
                updatedBook.setTitle(title);
            }

            if (!updatedBook.getISBNNumber().equals(isbn)) {
                updatedBook.setISBNNumber(isbn);
            }

            // check if author exists in database
            String authorQueryStr = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname";
            Query authorQuery = em.createQuery(authorQueryStr);
            authorQuery.setParameter("name", authorName);
            authorQuery.setParameter("surname", authorSurname);
            List<Author> authors = authorQuery.getResultList();
            // if author does not exists then add new one, select existing one otherwise
            if (authors.isEmpty()) {
                Author newAuthor = new Author(authorName, authorSurname);
                // update books field of author entity
                newAuthor.addBook(updatedBook);
                em.persist(newAuthor);
            } else {
                for (Author author : authors) {
                    author.addBook(updatedBook);
                }
            }

            //check if book genre exists in database
            String genreQueryStr = "SELECT c FROM BookGenre c WHERE c.name = :name";
            Query genreQuery = em.createQuery(genreQueryStr);
            genreQuery.setParameter("name", genre);
            List<BookGenre> genres = genreQuery.getResultList();
            // if genre does not exists then add new one, select existing one otherwise
            if (genres.isEmpty()) {
                BookGenre newGenre = new BookGenre(genre);
                // update books field of genre entity
                newGenre.addBook(updatedBook);
                em.persist(newGenre);
            } else {
                for (BookGenre bookGenre : genres) {
                    bookGenre.addBook(updatedBook);
                }
            }

            // catalog to book is one to one mapping so new catalog must be created

            if(quantity != updatedBook.getCatalog().getQuantity()) {
                Catalog catalog = new Catalog(quantity, quantity);
                catalog.setBook(updatedBook);
                Catalog oldCatalog = updatedBook.getCatalog();
                updatedBook.setCatalog(catalog);
                em.remove(oldCatalog);
                em.persist(catalog);
                em.persist(updatedBook);
            } else {
                Catalog catalog = updatedBook.getCatalog();
                catalog.setBook(updatedBook);
                em.persist(catalog);
                em.persist(updatedBook);
            }

        }

        catch ( Exception e ) {
                System.err.println("An error occurred during updating of a book: " + e);
        }
    }

    
    public void deleteBook(int id) {
        try {
            Book book = em.find(Book.class, id);

            Author author = book.getAuthor();
            author.removeBook(book);

            BookGenre genre = book.getGenre();
            genre.removeBook(book);

            Catalog catalog = book.getCatalog();
            em.remove(catalog);

            em.remove(book);

        } catch (Exception e) {
            System.err.println("An error occurred during book deletion. Id: " + id + "\n" + e);
        }
    }
}
