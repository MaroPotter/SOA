package com.agh.soa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "BookStore")
@SessionScoped
public class BookStore implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String type;
    private double price;
    private String currency;
    private int numberOfPages;
    private boolean isBookVisible;
    private List<Book> selectedBooks = new ArrayList<>();
    double sumOfOrder = 0.0;
    private static final ArrayList<Book> books
            = new ArrayList<Book>(Arrays.asList(
            new Book("Anioły i demony", "Dan Brown", "kryminał",80.00, "PLN", 350),
            new Book("Zaginiony symbol", "Dan Brown", "kryminał",70.00, "PLN", 359),
            new Book("Cyfrowa twierdza", "Dan Brown", "kryminał",60.00, "PLN", 421),
            new Book("Wild Symphony", "Dan Brown", "dziecięca", 56.00, "PLN", 253),
            new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "podróżnicza",40.00, "PLN", 386),
            new Book("Pan Wołodyjowski", "Henryk Sienkiewicz", "historyczna", 35.00, "PLN", 521),
            new Book("365 dni", "Blanka Lipińska", "romans", 40.00, "PLN", 602),
            new Book("Ten dzień", "Blanka Lipińska", "romans", 50.00, "PLN", 551),
            new Book("Kolejne 365 dni", "Blanka Lipińska", "romans", 45.00, "PLN", 520),
            new Book("After", "Anna Todd", "romans", 70.00, "PLN", 891)
    ));


    public ArrayList<Book> getBooks() {
        return books;
    }

//    public String addBook() {
//        Book book = new Book(title,author, type , price, currency, numberOfPages);
//        books.add(book);
//        return null;
//    }

    public String deleteBook(Book Book) {
        books.remove(Book);
        return null;
    }

    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

    public List<Book> getFilteredBookList() {
        TitleFilter titleFilter = (TitleFilter) request.getSession().getAttribute("TitleFilter");
        AuthorFilter authorFilter = (AuthorFilter) request.getSession().getAttribute("AuthorFilter");
        TypeFilter typeFilter = (TypeFilter) request.getSession().getAttribute("TypeFilter");
        PriceFilter priceFilter = (PriceFilter) request.getSession().getAttribute("PriceFilter");
        CurrencyFilter currencyFilter = (CurrencyFilter) request.getSession().getAttribute("CurrencyFilter");
        PageFilter pageFilter = (PageFilter) request.getSession().getAttribute("PageFilter");


        List<Book> result = titleFilter.applyFilter(books);
        List<Book> result2 = authorFilter.applyFilter(result);
        List<Book> result3 = typeFilter.applyFilter(result2);
        List<Book> result4 = priceFilter.applyFilter(result3);
        List<Book> result5 = currencyFilter.applyFilter(result4);
        List<Book> result6 = pageFilter.applyFilter(result5);
        System.out.println(result6);

        this.isBookVisible = titleFilter.isTitleVisible() || authorFilter.isAuthorVisible() || typeFilter.isTypeVisible()
                || priceFilter.isPriceVisible() || currencyFilter.isCurrencyVisible() || pageFilter.isPagesVisible();

        return result6;
    }

    public String submitOrder() {
        selectedBooks.clear();
        sumOfOrder = 0;

        for (Book book: getFilteredBookList())
        {
            if (book.selected){
                selectedBooks.add(book);
                sumOfOrder += book.getPrice();
            }
        }
        return "summary";
    }

    public boolean isBookVisible() {
        return isBookVisible;
    }

    public void setBookVisible(boolean bookVisible) {
        isBookVisible = bookVisible;
    }

    public List<Book> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<Book> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }

    public double getSumOfOrder() {
        return sumOfOrder;
    }

    public void setSumOfOrder(double sumOfOrder) {
        this.sumOfOrder = sumOfOrder;
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
