package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "PageFilter")
@SessionScoped
public class PageFilter extends Filter {
    private int numberOfPagesMin = 0;
    private int numberOfPagesMax = 1000;

    boolean filterByPages = false;
    boolean isPagesVisible = true;

    @Override
    boolean bookMeetsTheConstraints(Book book) {
        return book.getNumberOfPages()>=numberOfPagesMin && book.getNumberOfPages()<=numberOfPagesMax;
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
