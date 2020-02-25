package com.kozhukhar.carshop_online.util.purchase.cart.event;

import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import com.kozhukhar.carshop_online.web.service.ProductService;

public class MinusToCartCommand implements CartCommand {
    @Override
    public Integer execute(ProductService productService, Integer productId, CartManager cartManager) {
        Product product = null;
        if (productId != null) {
            product = productService.getProductById(productId);
            cartManager.deleteOne(product);
        }
        return cartManager.getValue(product);
    }
}
