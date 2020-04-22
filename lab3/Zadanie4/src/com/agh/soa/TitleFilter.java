package com.agh.soa;

import org.w3c.dom.ls.LSOutput;

import javax.ejb.SessionBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "TitleFilter")
@SessionScoped
public class TitleFilter extends Filter {
    public String title;
    boolean filterByTitle = false;
    boolean isTitleVisible = true;



    @Override
    boolean bookMeetsTheConstraints(Book book) {
        if(filterByTitle) {
            String pattern = ".*" + title + ".*";
            return book.getTitle().matches(pattern);
        } else {
            return false;
        }
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

