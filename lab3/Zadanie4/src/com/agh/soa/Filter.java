package com.agh.soa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Filter {
    abstract boolean bookMeetsTheConstraints(Book b);
    List<Book> applyFilter(ArrayList<Book> books) {
        List<Book> filteredBookList = new ArrayList<>(books);
        for (Book book : books) {
            if(bookMeetsTheConstraints(book)) {
                ;
            } else {
                filteredBookList.remove(book);

            }

        }
        return filteredBookList;
    }
}
