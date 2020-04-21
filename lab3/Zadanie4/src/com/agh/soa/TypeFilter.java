package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "TypeFilter")
@SessionScoped
public class TypeFilter extends Filter {
    private String type;
    public boolean filterByType = false;
    boolean isTypeVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
        String pattern = ".*" + type + ".*";
        return book.getType().matches(pattern);
    }

    // getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFilterByType() {
        return filterByType;
    }

    public void setFilterByType(boolean filterByType) {
        this.filterByType = filterByType;
    }

    public boolean isTypeVisible() {
        return isTypeVisible;
    }

    public void setTypeVisible(boolean typeVisible) {
        isTypeVisible = typeVisible;
    }
}
