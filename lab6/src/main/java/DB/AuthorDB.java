package DB;

import entities.Author;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(AuthorDB.class)
public class AuthorDB {
    @PersistenceContext(name = "book-persistence-unit")
    private EntityManager em;

    public List<Author> getAllAuthors() {
        try {
            Query q = em.createQuery("FROM Author ", Author.class);
            return (List<Author>) q.getResultList();
        }
        catch(Exception e) {
            System.err.println("An error occurred during selecting author: " + e);
        }
        return null;
    }
    
    public void addAuthor(String name, String surname) {
        try {

            Author author = new Author(name, surname);
            em.persist(author);

        } catch (Exception e){
            System.err.println("An error occurred during adding author: " + e);
        }
    }

    
    public List<Author> searchByNameAndSurname(String name, String surname) {
        String authorQueryStr = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname";
        Query authorQuery = em.createQuery(authorQueryStr);
        authorQuery.setParameter("name", name);
        authorQuery.setParameter("surname", surname);
        List<Author> authors = authorQuery.getResultList();

        return authors;
    }

    
    public void deleteAuthor(int id) {
        try {
            Author author = em.find(Author.class, id);
            em.remove(author);
        } catch (Exception e){
            System.err.println("An error occurred during author deletion. Id: " + id + "\n" + e);
        }
    }

    
    public void updateAuthor(int id, Author author) {
        try {
            Author updateObject = em.find(Author.class, id);

            if( author.getName() != null )
                updateObject.setName(author.getName());

            if( author.getSurname() != null )
                updateObject.setSurname(author.getSurname());

            em.persist(updateObject);
        } catch (Exception e) {
            System.err.println("An error occurred during updating a book object. Id = "+ id + "\n" + e);
        }
    }
}
