package com.kozhukhar.carshop_online.db.storage;

public enum SortType {

    NAME("Name"),
    PRICE("Price"),
    NAME_DESC("Name desc"),
    PRICE_DESC("Price desc");

    private String name;

    SortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
