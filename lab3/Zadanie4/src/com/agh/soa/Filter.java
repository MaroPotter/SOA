package com.agh.soa;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter {
    abstract boolean bookMeetsTheConstraints(Book b);
    List<Book> applyFilter(List<Book> books) {
        List<Book> filteredBookList = new ArrayList<>();
        for (Book book : books) {
            if(bookMeetsTheConstraints(book)) {
                filteredBookList.add(book);
            }
        }
        return filteredBookList;
    }
}
