package com.agh.soa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Filter {
    boolean isFiltered = false;

    abstract boolean bookMeetsTheConstraints(Book b);

    List<Book> applyFilter(List<Book> books) {
        List<Book> filteredBookList = new ArrayList<>(books);
        for (Book book : books) {
            if (isFiltered) {
                if (!bookMeetsTheConstraints(book)) {
                    filteredBookList.remove(book);
                }
            }
        }
        return filteredBookList;
    }


    public boolean getIsFiltered() { return isFiltered; }
    public void setIsFiltered(boolean isFiltered) {
        this.isFiltered = isFiltered;
    }

}