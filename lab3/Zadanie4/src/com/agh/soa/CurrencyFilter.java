package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "CurrencyFilter")
@SessionScoped
public class CurrencyFilter extends Filter {
    private String currency;
    boolean filterByCurrency = false;
    boolean isCurrencyVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
        return book.getCurrency().equals(currency);
    }

    // getters and setters
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isFilterByCurrency() {
        return filterByCurrency;
    }

    public void setFilterByCurrency(boolean filterByCurrency) {
        this.filterByCurrency = filterByCurrency;
    }

    public boolean isCurrencyVisible() {
        return isCurrencyVisible;
    }

    public void setCurrencyVisible(boolean currencyVisible) {
        isCurrencyVisible = currencyVisible;
    }
}