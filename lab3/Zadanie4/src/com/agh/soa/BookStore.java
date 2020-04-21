package com.agh.soa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "BookStore", eager = true)
@SessionScoped
public class BookStore implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String type;
    private double price;
    private String currency;
    private int numberOfPages;
    private static final ArrayList<Book> books
            = new ArrayList<Book>(Arrays.asList(
            new Book("Anioły i demony", "Dan Brown", "kryminał",80.00, "złoty", 350),
            new Book("Zaginiony symbol", "Dan Brown", "kryminał",70.00, "złoty", 359),
            new Book("Cyfrowa twierdza", "Dan Brown", "kryminał",60.00, "złoty", 421),
            new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "podróżnicza",40.00, "złoty", 386)
    ));


    public ArrayList<Book> getBooks() {
        return books;
    }

//    public String addBook() {
//        Book book = new Book(title,author,age,salary);
//        books.add(book);
//        return null;
//    }

    public String deleteBook(Book Book) {
        books.remove(Book);
        return null;
    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
}
