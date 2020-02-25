package com.kozhukhar.carshop.view;

import com.kozhukhar.carshop.anotation.AnnotationLocalize;

public class InterfaceLib {


    private String cart;

    private String productName;

    private String quantityInCart;

    private String price;

    private String catalog;

    private static final String DEFAULT = "default";

    public InterfaceLib() {
        cart = DEFAULT;
        productName = DEFAULT;
        quantityInCart = DEFAULT;
        catalog = DEFAULT;
    }

    public InterfaceLib(String cart, String productName, String quantityInCart) {
        this.cart = cart;
        this.productName = productName;
        this.quantityInCart = quantityInCart;
    }

    public String getCart() {
        return cart;
    }

    public String getProductName() {
        return productName;
    }

    public String getQuantityInCart() {
        return quantityInCart;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getPrice() {
        return price;
    }
}
