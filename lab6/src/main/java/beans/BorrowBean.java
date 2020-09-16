package beans;

import entities.Book;
import entities.Borrow;
import entities.Catalog;
import entities.Reader;
import DB.BorrowDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;

@ManagedBean(name = "BorrowBean")
@SessionScoped
public class BorrowBean {
    @EJB
    BorrowDB borrowingDB;

    private Book book;
    private Reader reader;
    private Catalog catalog;

    private Map<Integer, Boolean> canReturnMap = new LinkedHashMap<>();
    private boolean showReturnBookField;
    private Integer selectedReaderId;
    private Integer selectedBookId;

    public List<Borrow> getAllBorrowings() {
        Date today = Calendar.getInstance().getTime();
        Map<Integer, Boolean> canReturnMap = new LinkedHashMap<>();

        List<Borrow> borrowings = borrowingDB.getAllBookBorrowing();
        for (Borrow borrowing: borrowings) {
            Date returnDate = borrowing.getToDate();
            if ( returnDate.compareTo(today) >= 0) {
                canReturnMap.put(borrowing.getId(), true);
            } else {
                canReturnMap.put(borrowing.getId(), false);
            }
        }

        this.setCanReturnMap(canReturnMap);
        this.setShowReturnBookField(canReturnMap.containsValue(true));

        return borrowings;
    }

    public String borrowBook(){
        borrowingDB.borrowBook(this.getSelectedReaderId(), this.getSelectedBookId());
        this.setEmptyValues();

        return "/borrows/borrows";
    }

    public void returnBook(int id) {
        borrowingDB.returnBook(id);
    }


    public void setEmptyValues () {
        this.setSelectedBookId(null);
        this.setSelectedReaderId(null);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Integer getSelectedReaderId() {
        return selectedReaderId;
    }

    public void setSelectedReaderId(Integer selectedReaderId) {
        this.selectedReaderId = selectedReaderId;
    }

    public Integer getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Integer selectedBookId) {
        this.selectedBookId = selectedBookId;
    }

    public Map<Integer, Boolean> getCanReturnMap() {
        return canReturnMap;
    }

    public void setCanReturnMap(Map<Integer, Boolean> canReturnMap) {
        this.canReturnMap = canReturnMap;
    }

    public boolean isShowReturnBookField() {
        return showReturnBookField;
    }

    public void setShowReturnBookField(boolean showReturnBookField) {
        this.showReturnBookField = showReturnBookField;
    }
}
