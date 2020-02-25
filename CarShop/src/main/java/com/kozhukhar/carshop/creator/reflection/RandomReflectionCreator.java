package com.kozhukhar.carshop.creator.reflection;

import java.util.Random;

public class RandomReflectionCreator implements ReflectionCreator {

    private Random random;

    private String type;

    public RandomReflectionCreator() {
        type = "DEFAULT";
        random = new Random();
    }

    @Override
    public Integer getInt(String msg) {
        Integer intValue = Integer.valueOf(String.format("%04d", random.nextInt(10000)));
        System.out.println(msg + " " + intValue);
        return intValue;
    }

    @Override
    public String getString(String msg) {
        System.out.println(msg + " " + type);
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
