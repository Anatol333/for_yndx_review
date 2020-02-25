package com.kozhukhar.carshop.entity.embeded;

import com.kozhukhar.carshop.anotation.AnnotationLocalize;

import java.io.Serializable;
import java.util.Objects;

/**
 * Primary key for Transport object
 */
public class TransportEmbedded implements Serializable {

    private String name;

    private String model;

    public TransportEmbedded(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public TransportEmbedded() {

    }

    public String getName() {
        return name;
    }

    @AnnotationLocalize(keyName = "create.set_name")
    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    @AnnotationLocalize(keyName = "create.set_model")
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportEmbedded that = (TransportEmbedded) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + model;
    }
}
