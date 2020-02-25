package com.kozhukhar.task7;

public class ProductImpl implements Product {

    private Integer price;

    private String name;

    public ProductImpl() {
        price = 100;
        name = "Test name";
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductImpl{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
