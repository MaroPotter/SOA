package beans;

import entities.Book;
import DB.BookDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "BookBean")
@SessionScoped
public class BookBean {
    @EJB
    BookDB bookDB;

    private String title;
    private BigInteger ISBNNumber;
    private String authorName;
    private String authorSurname;
    private String genre;
    private Integer quantity;
    private Integer available;

    private Integer selectedBookId;

    public List<Book> getAllBooks() {
        return bookDB.getAllBooks();
    }

    public String addBook() {
        bookDB.addBook(this.getTitle(), this.getISBNNumber(), this.getAuthorName(), this.getAuthorSurname(),
                this.getGenre(), this.getQuantity());
        this.setEmptyValues();

        return "/books/books";
    }

    public String updateBook() {
        bookDB.updateBook(this.getSelectedBookId(), this.getTitle(), this.getISBNNumber(), this.getAuthorName(), this.getAuthorSurname(),
                this.getGenre(), this.getQuantity());
        this.setEmptyValues();

        return "/books/books";
    }

    public String deleteBook() {
        bookDB.deleteBook(this.getSelectedBookId());
        this.setEmptyValues();

        return "/books/books";
    }

    public Map<String, Integer> getBooksMap() {
        Map<String, Integer> booksMap = new LinkedHashMap<>();

        String label = "";
        List <Book> books = bookDB.getAllBooks();
        for (Book book : books) {
            label = book.getTitle() + ", " + book.getAuthor().getName() + " " + book.getAuthor().getSurname();
            booksMap.put(label, book.getId());
        }

        return booksMap;
    }

    public void onBookSelection (AjaxBehaviorEvent ajaxBehaviorEvent) {
        List<Book> books = bookDB.getAllBooks();

        if ( this.getSelectedBookId() == null ) {
            this.setEmptyValues();
        } else {
            for (Book book : books) {
                if ( this.getSelectedBookId() == book.getId() ) {
                    this.setTitle(book.getTitle());
                    this.setISBNNumber(book.getISBNNumber());
                    this.setAuthorName(book.getAuthor().getName());
                    this.setAuthorSurname(book.getAuthor().getSurname());
                    this.setGenre(book.getGenre().getName());
                    this.setQuantity(book.getCatalog().getQuantity());
                }
            }
        }
    }

    public void setEmptyValues() {
        this.setTitle(null);
        this.setISBNNumber(null);
        this.setAuthorName(null);
        this.setAuthorSurname(null);
        this.setGenre(null);
        this.setQuantity(null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getISBNNumber() {
        return ISBNNumber;
    }

    public void setISBNNumber(BigInteger ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Integer selectedBookId) {
        this.selectedBookId = selectedBookId;
    }
}
