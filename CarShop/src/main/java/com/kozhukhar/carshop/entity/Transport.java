package com.kozhukhar.carshop.entity;

import com.kozhukhar.carshop.anotation.AnnotationLocalize;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;

import java.io.Serializable;
import java.util.Objects;

/**
 * Transport entity.
 * <p>
 * Main entity of all transports with main tags such as name and model.
 * This field are included in equals and hashcode methods.
 * <p>
 * Implements an Entity serializable interface.
 *
 * @author Anatol_Kozhukhar
 */
public class Transport implements Serializable {

    /**
     *  Embedded primary key which contains NAME and MODEL
     *  of this current transport.
     */
    private TransportEmbedded key;

    private Integer maxSpeed;

    private Integer price;

    public Transport() {
        key = new TransportEmbedded();
    }

    public Transport(TransportEmbedded key, Integer maxSpeed, Integer price) {
        this.key = key;
        this.maxSpeed = maxSpeed;
        this.price = price;
    }

    public Transport(String name, String model, Integer maxSpeed) {
        key = new TransportEmbedded(name, model);
        this.maxSpeed = maxSpeed;
    }

    public Transport(TransportEmbedded key) {
        this.key = key;
    }

    public Integer getPrice() {
        return price;
    }

    @AnnotationLocalize(keyName = "create.set_price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return key.getName();
    }

    public void setName(String name) {
        key.setName(name);
    }

    public String getModel() {
        return key.getModel();
    }

    public void setModel(String model) {
        key.setModel(model);
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public TransportEmbedded getKey() {
        return key;
    }

    public void setKey(TransportEmbedded key) {
        this.key = key;
    }

    /**
     * Checking object between themselves by name and model of transport.
     *
     * @param o checked parameters
     * @return boolean result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Transport)) return false;
        Transport transport = (Transport) o;

        if (!Objects.equals(getName(), transport.getName())) return false;
        return Objects.equals(getModel(), transport.getModel());
    }

    /**
     * Checking by hashcode at first.
     *
     * @return hash of Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getModel());
    }

    @Override
    public String toString() {
        return "Transport{" +
                "key=" + key +
                ", price=" + price +
                '}';
    }
}
