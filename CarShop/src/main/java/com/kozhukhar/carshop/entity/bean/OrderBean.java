package com.kozhukhar.carshop.entity.bean;

import java.util.List;

public class OrderBean {

    private List<CartEntry> cartEntry;

    private Integer fullOrderPrice;

    public OrderBean() {
    }

    public OrderBean(List<CartEntry> cartEntry, Integer fullOrderPrice) {
        this.cartEntry = cartEntry;
        this.fullOrderPrice = fullOrderPrice;
    }

    public List<CartEntry> getCartEntry() {
        return cartEntry;
    }

    public void setCartEntry(List<CartEntry> cartEntry) {
        this.cartEntry = cartEntry;
    }

    public Integer getFullOrderPrice() {
        return fullOrderPrice;
    }

    public void setFullOrderPrice(Integer fullOrderPrice) {
        this.fullOrderPrice = fullOrderPrice;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "product=" + cartEntry +
                ", fullOrderPrice=" + fullOrderPrice +
                '}';
    }
}
