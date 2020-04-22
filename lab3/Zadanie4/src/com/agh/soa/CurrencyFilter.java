package com.agh.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "CurrencyFilter", eager = true)
@SessionScoped
public class CurrencyFilter extends Filter {
    private String currency = "PLN";
    private boolean isCurrencyVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
            return book.getCurrency().equals(currency);
    }
    public String currencyForBook(Book book)
    {
        return PriceFilter.inPLN ? "PLN" : book.getCurrency();
    }


    // getters and setters

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isCurrencyVisible() {
        return isCurrencyVisible;
    }

    public void setCurrencyVisible(boolean currencyVisible) {
        isCurrencyVisible = currencyVisible;
    }
}