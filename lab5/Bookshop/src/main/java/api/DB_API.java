package api;

import db.Book;

import java.util.List;

public interface DB_API {
    List<Book> getAllBooks();
    void addBook(Book book);
    void deleteBookById(int toDeleteId);
    void updateBook(int bookID, Book book);
}