package com.kozhukhar.carshop.entity;

import java.io.Serializable;

public class Train extends Transport implements Serializable {

    private Integer trackWidth;

    private Integer maxPower;

    public Train() {
    }

    public Train(String name, String model, Integer maxSpeed, Integer trackWidth, Integer maxPower) {
        super(name, model, maxSpeed);
        this.trackWidth = trackWidth;
        this.maxPower = maxPower;
    }

    public Integer getTrackWidth() {
        return trackWidth;
    }

    public void setTrackWidth(Integer trackWidth) {
        this.trackWidth = trackWidth;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    @Override
    public String toString() {
        return "Train{" +
                " name=" + getName() +
                ", model=" + getModel() +
                ", maxSpeed=" + getMaxSpeed() +
                ", trackWidth=" + trackWidth +
                ", maxPower=" + maxPower +
                '}';
    }
}
