package entities;

import javax.persistence.*;

@Entity
@Table(schema = "library", name = "catalog")
public class Catalog {
    private int id;
    private int quantity;
    private int available;
    private Book book;

    public Catalog() {
    }

    public Catalog(int quantity, int available) {
        this.quantity = quantity;
        this.available = available;
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

    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "available", nullable = false)
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }

    @OneToOne(mappedBy = "catalog")
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public void borrowBook() {
        this.available -= 1;
    }

    public void returnBook() {
        this.available += 1;
    }
}
