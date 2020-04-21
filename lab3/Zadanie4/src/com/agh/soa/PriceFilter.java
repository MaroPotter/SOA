package com.agh.soa;
import CurrencyExchange.Exchanger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "PriceFilter")
@SessionScoped
public class PriceFilter extends Filter {
    private double priceMin = 0.0;
    private double priceMax = 250.0;
    private boolean inPLN = true;
    private Exchanger exchanger= new Exchanger();
    private boolean filterByPrice = false;
    private boolean isPriceVisible = true;


    @Override
    boolean bookMeetsTheConstraints(Book book) {
        return priceForBook(book)>=priceMin && priceForBook(book)<=priceMax;
    }
    private double priceForBook(Book book)
    {
        return this.inPLN ? Math.ceil(exchanger.exchangeToPLN(book.getCurrency(),book.getPrice())) : book.getPrice();
    }

    // getters and setters
    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    public boolean isFilterByPrice() {
        return filterByPrice;
    }

    public void setFilterByPrice(boolean filterByPrice) {
        this.filterByPrice = filterByPrice;
    }

    public boolean isPriceVisible() {
        return isPriceVisible;
    }

    public void setPriceVisible(boolean priceVisible) {
        isPriceVisible = priceVisible;
    }
    public boolean getInPLN() {
        return inPLN;
    }

    public void setInPLN(boolean inPLN) {
        this.inPLN = inPLN;
    }

}
