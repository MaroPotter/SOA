package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "TypeFilter", eager = true)
@SessionScoped
public class TypeFilter extends Filter {
    private String type = "";
    private boolean isTypeVisible = true;


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

    public boolean isTypeVisible() {
        return isTypeVisible;
    }

    public void setTypeVisible(boolean typeVisible) {
        isTypeVisible = typeVisible;
    }
}
