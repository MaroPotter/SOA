package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "TitleFilter")
@SessionScoped
public class TitleFilter extends Filter {
    public String title;
    boolean filterByTitle = false;
    boolean isTitleVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
        String pattern = ".*" + title + ".*";
        return book.getTitle().matches(pattern);
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFilterByTitle() {
        return filterByTitle;
    }

    public void setFilterByTitle(boolean filterByTitle) {
        this.filterByTitle = filterByTitle;
    }

    public boolean isTitleVisible() {
        return isTitleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        isTitleVisible = titleVisible;
    }
}

