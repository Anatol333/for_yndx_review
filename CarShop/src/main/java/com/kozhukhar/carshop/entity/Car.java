package com.kozhukhar.carshop.entity;

import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;

import java.io.Serializable;

public class Car extends Transport implements Serializable {

    private Integer countOfWheels;

    private Integer diameterOfWeal;

    private String engineType;

    public Car() {
    }

    public Car(TransportEmbedded key, Integer maxSpeed, Integer price) {
        super(key, maxSpeed, price);
    }

    public Car(String name, String model, Integer maxSpeed, Integer price, Integer countOfWheels, Integer diameterOfWeal, String engineType) {
        super(name, model, maxSpeed);
        this.countOfWheels = countOfWheels;
        this.diameterOfWeal = diameterOfWeal;
        this.engineType = engineType;
    }

    public Integer getCountOfWheels() {
        return countOfWheels;
    }

    public void setCountOfWheels(Integer countOfWheels) {
        this.countOfWheels = countOfWheels;
    }

    public Integer getDiameterOfWeal() {
        return diameterOfWeal;
    }

    public void setDiameterOfWeal(Integer diameterOfWeal) {
        this.diameterOfWeal = diameterOfWeal;
    }


    @Override
    public String toString() {
        return "Car{" +
                " name=" + getName() +
                ", model=" + getModel() +
                ", maxSpeed=" + getMaxSpeed() +
                ", countOfWeals=" + countOfWheels +
                ", engineType=" + engineType +
                ", diameterOfWeal=" + diameterOfWeal +
                '}';
    }
}
