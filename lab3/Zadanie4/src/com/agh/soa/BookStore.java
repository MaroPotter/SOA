package com.agh.soa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.annotation.ManagedProperty;
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
//        Book book = new Book(title,author, type , price, currency, numberOfPages);
//        books.add(book);
//        return null;
//    }

    public String deleteBook(Book Book) {
        books.remove(Book);
        return null;
    }
    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

//    @SuppressWarnings("unchecked")
//    public static <T> T findBean(String beanName) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
//    }

        public List<Book> getFilteredBookList() {
            TitleFilter titleFilter = (TitleFilter) request.getSession().getAttribute("TitleFilter");
            List<Book> result = titleFilter.applyFilter(books);
//        List<Book> result2 = authorFilter.applyFilter(books);
//        List<Book> result3 = typeFilter.applyFilter(books);
//        PriceFilter priceFilter = new PriceFilter();
//        List<Book> result4 = priceFilter.applyFilter(books);
//        CurrencyFilter currencyFilter = new CurrencyFilter();
//        List<Book> result5 = currencyFilter.applyFilter(books);
//        PageFilter pageFilter = new PageFilter();
//        List<Book> result6 = pageFilter.applyFilter(books);
//        result.retainAll(result2);
//        System.out.println(result);
//        result.retainAll(result3);
        System.out.println(result);


        return result;

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
