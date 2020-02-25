package com.kozhukhar.carshop.entity;

import java.io.Serializable;

public class Truck extends Car implements Serializable {

    private Float volume;

    private Float loadCapacity;

    public Truck() {
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(float loadCapacity) {
        this.loadCapacity = loadCapacity;
    }


    @Override
    public String toString() {
        return "Truck{" +
                " name=" + getName() +
                ", model=" + getModel() +
                ", maxSpeed=" + getMaxSpeed() +
                ", volume=" + volume +
                ", loadCapacity=" + loadCapacity +
                '}';
    }
}
