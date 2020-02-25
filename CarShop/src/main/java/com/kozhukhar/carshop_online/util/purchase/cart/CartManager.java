package com.kozhukhar.carshop_online.util.purchase.cart;

import com.kozhukhar.carshop_online.db.dto.Product;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CartManager implements Cart<Product, Integer> {

    private Map<Product, Integer> cartMap;

    private static final Integer PRODUCT_VALUE = 1;
    private static final Integer NULL_PRODUCT = 0;

    public CartManager() {
        cartMap = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Product product) {
        if (product != null) {
            Integer countProduct = cartMap.getOrDefault(product, 0);
            cartMap.put(product, countProduct + PRODUCT_VALUE);
        }
    }

    @Override
    public Integer getValue(Product product) {
        return Optional.ofNullable(product).map(cartMap::get).orElse(0);
    }

    @Override
    public void delete(Product product) {
        cartMap.remove(product);
    }

    @Override
    public void deleteOne(Product product) {
        if (cartMap.get(product).equals(PRODUCT_VALUE)) {
            delete(product);
        } else {
            cartMap.put(product, cartMap.get(product) - PRODUCT_VALUE);
        }
    }

    @Override
    public Map<Product, Integer> findAll() {
        return cartMap;
    }

    @Override
    public Integer numberOfProducts() {
        return cartMap.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public Float getFullPrice() {
        float price = 0.0f;
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        return price;
    }
}
