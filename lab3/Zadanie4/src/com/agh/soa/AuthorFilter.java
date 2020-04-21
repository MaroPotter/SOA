package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "AuthorFilter")
@SessionScoped
public class AuthorFilter extends Filter {
    private String author;
    boolean filterByAuthor = false;
    boolean isAuthorVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
        String pattern = ".*" + author + ".*";
        return book.getAuthor().matches(pattern);
    }

    // getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFilterByAuthor() {
        return filterByAuthor;
    }

    public void setFilterByAuthor(boolean filterByAuthor) {
        this.filterByAuthor = filterByAuthor;
    }

    public boolean isAuthorVisible() {
        return isAuthorVisible;
    }

    public void setAuthorVisible(boolean authorVisible) {
        isAuthorVisible = authorVisible;
    }
}