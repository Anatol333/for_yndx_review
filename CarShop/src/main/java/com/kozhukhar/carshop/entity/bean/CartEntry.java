package com.kozhukhar.carshop.entity.bean;

import com.kozhukhar.carshop.entity.Transport;

import java.io.Serializable;

public class CartEntry implements Serializable {
    private Object objectOfProduct;
    private Integer countOfProduct;
    private Integer price;

    public CartEntry() {
    }

    public CartEntry(Object objectOfProduct, Integer countOfProduct, Integer price) {
        this.objectOfProduct = objectOfProduct;
        this.countOfProduct = countOfProduct;
        this.price = price;
    }

    public CartEntry(Object objectOfProduct, Integer countOfProduct) {
        this.objectOfProduct = objectOfProduct;
        this.countOfProduct = countOfProduct;
    }

    public CartEntry(Transport transport) {
        this.objectOfProduct = transport.getKey();
        this.countOfProduct = 0;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getObjectOfProduct() {
        return objectOfProduct;
    }

    public void setObjectOfProduct(Object objectOfProduct) {
        this.objectOfProduct = objectOfProduct;
    }

    public Integer getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Integer countOfProduct) {
        this.countOfProduct = countOfProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "objectOfProduct=" + objectOfProduct +
                ", countOfProduct=" + countOfProduct +
                ", price=" + price +
                '}';
    }
}
