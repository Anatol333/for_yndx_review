package com.kozhukhar.carshop_online.util.purchase;

public enum OrderStatus {

    ACCEPTED("accepted"),
    CONFIRMED("confirmed"),
    FORMED("formed"),
    SENT("sent"),
    COMPLETED("completed"),
    CANCELED("canceled");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
