package com.kozhukhar.carshop_online.db.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class Product {

    private Integer id;

    private String name;

    private Float price;

    private String category;

    private String producer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
