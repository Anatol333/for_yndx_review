package com.kozhukhar.carshop.util;

public enum Localization {

    RU("ru"),
    EN("en");


    private String name;

    Localization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
