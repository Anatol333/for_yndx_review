package com.kozhukhar.carshop_online.util.purchase;

public enum PayTypes {

    CARD("card"),
    POST_OFFICE("post_office");

    private String name;

    PayTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
