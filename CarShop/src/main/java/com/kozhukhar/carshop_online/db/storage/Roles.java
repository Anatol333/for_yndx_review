package com.kozhukhar.carshop_online.db.storage;

public enum Roles {

    ADMIN("admin"),
    USER("user");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
