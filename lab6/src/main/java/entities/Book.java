package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(schema = "library", name = "book")
public class Book {
    private int id;
    private String title;
    private BigInteger ISBNNumber;
    private Author author;
    private BookGenre genre;
    private Catalog catalog;
    private List<Borrow> borrowing;

    public Book() {
    }

    public Book(String title, BigInteger ISBNNumber) {
        this.title=title;
        this.ISBNNumber=ISBNNumber;
    }
    public Book(String title, BigInteger ISBNNumber, String authorName, String authorSurname, String genre) {
        this.title=title;
        this.ISBNNumber=ISBNNumber;
        this.author = new Author(authorName, authorSurname);
        this.genre = new BookGenre(genre);
    }


    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // ISBN number is a 13-digit number so column type must be numeric(13,0)
    @Column(name = "ISBNNumber", nullable = false, columnDefinition = "numeric(13,0)")
    public BigInteger getISBNNumber() {
        return ISBNNumber;
    }
    public void setISBNNumber(BigInteger ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    @ManyToOne
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToOne
    public BookGenre getGenre() {
        return genre;
    }
    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    @OneToOne
    @JoinColumn(name = "catalog_id")
    public Catalog getCatalog() {
        return catalog;
    }
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @OneToMany(mappedBy = "book")
    public List<Borrow> getBorrowing() {
        return borrowing;
    }
    public void setBorrowing(List<Borrow> borrowing) {
        this.borrowing = borrowing;
    }

    @Override
    public String toString() {
        return "'" + title + "\' " + author;
    }
}
