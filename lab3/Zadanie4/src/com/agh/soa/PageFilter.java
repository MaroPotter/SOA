package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "PageFilter", eager = true)
@SessionScoped
public class PageFilter extends Filter {
    private int numberOfPagesMin = 0;
    private int numberOfPagesMax = 1000;

    boolean filterByPages = false;
    boolean isPagesVisible = true;

    @Override
    boolean bookMeetsTheConstraints(Book book) {
        if(filterByPages) {
            return book.getNumberOfPages() >= numberOfPagesMin && book.getNumberOfPages() <= numberOfPagesMax;
        } else {
            return false;
        }
    }

    // getters and setters
    public int getNumberOfPagesMin() {
        return numberOfPagesMin;
    }

    public void setNumberOfPagesMin(int numberOfPagesMin) {
        this.numberOfPagesMin = numberOfPagesMin;
    }

    public int getNumberOfPagesMax() {
        return numberOfPagesMax;
    }

    public void setNumberOfPagesMax(int numberOfPagesMax) {
        this.numberOfPagesMax = numberOfPagesMax;
    }

    public boolean isFilterByPages() {
        return filterByPages;
    }

    public void setFilterByPage(boolean filterByPages) {
        this.filterByPages = filterByPages;
    }

    public boolean isPagesVisible() {
        return isPagesVisible;
    }

    public void setPagesVisible(boolean pageVisible) {
        isPagesVisible = pageVisible;
    }


}
