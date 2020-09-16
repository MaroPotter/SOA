import api.DB_API;
import db.Book;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "BookBean")
@SessionScoped
public class BookBean
{
    @EJB
    DB_API dataBase;
    
    String authorSurname;
    String authorName;
    String bookTitle;
    String isbnNumber;
    Integer releaseDate;
    BigDecimal price;
    Integer selectedBookId;
    
    public String addBook()
    {
        Book bookEntity = new Book(this.authorName,this.authorSurname,this.bookTitle,
                this.isbnNumber,this.releaseDate,this.price);
        dataBase.addBook(bookEntity);
        this.setEmptyValues();

        return "BOOK_ADDED";
    }

    public String deleteBook()
    {
        System.out.println("BOOK ID TO REMOVE: " + this.getSelectedBookId());
        dataBase.deleteBookById(this.getSelectedBookId());
        this.setEmptyValues();

        return "BOOK_DELETED";
    }

    public String editBook()
    {
        Book bookEntity = new Book(this.getAuthorName(), this.getAuthorSurname(), this.getBookTitle(),
                this.getIsbnNumber(), this.getReleaseDate(), this.getPrice());
        dataBase.updateBook(this.getSelectedBookId(), bookEntity);
        this.setEmptyValues();

        return "BOOK_UPDATED";
    }

    public List<Book> getAllBooks()
    {
        return dataBase.getAllBooks();
    }

    public Map<String, Integer> getBooksMap() {
        Map<String, Integer> booksMap = new LinkedHashMap<>();

        String label = "";
        List <Book> books = dataBase.getAllBooks();
        for (Book b : books) {
            label = b.getAuthorName() + " " + b.getAuthorSurname() + ": " + b.getBookTitle();
            booksMap.put(label, b.getId());
        }

        return booksMap;
    }

    public void onBookSelection (AjaxBehaviorEvent ajaxBehaviorEvent) {
        List<Book> books = dataBase.getAllBooks();

        if ( this.getSelectedBookId() == null ) {
            this.setEmptyValues();
        } else {
            for (Book book : books) {
                if ( book.getId() == this.getSelectedBookId()) {
                    this.authorName = book.getAuthorName();
                    this.authorSurname = book.getAuthorSurname();
                    this.bookTitle = book.getBookTitle();
                    this.isbnNumber = book.getIsbnNumber();
                    this.releaseDate = book.getReleaseDate();
                    this.price = book.getPrice();
                }
            }
        }
    }

    public String onBackButton () {
        this.setEmptyValues();
        return "ON_BACK_CALLBACK";
    }

    public void setEmptyValues () {
        this.authorName = null;
        this.authorSurname = null;
        this.bookTitle = null;
        this.isbnNumber = null;
        this.releaseDate = null;
        this.price = null;
        this.setSelectedBookId(null);
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Integer selectedBookId) {
        this.selectedBookId = selectedBookId;
    }
}
