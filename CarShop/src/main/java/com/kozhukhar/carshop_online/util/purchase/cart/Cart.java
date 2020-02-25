package com.kozhukhar.carshop_online.util.purchase.cart;

import java.util.Map;

public interface Cart<K, V> {

    void add(K product);

    Integer getValue(K product);

    void delete(K product);

    void deleteOne(K product);

    Integer numberOfProducts();

    Map<K, V> findAll();

    Float getFullPrice();
}
