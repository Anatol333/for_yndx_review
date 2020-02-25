package com.kozhukhar.carshop_online.util.purchase.cart.event;

import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import com.kozhukhar.carshop_online.web.service.ProductService;

public class AddToCartCommand implements CartCommand {

    @Override
    public Integer execute(ProductService productService, Integer productId, CartManager cartManager) {
        if (productId != null) {
            Product product = productService.getProductById(productId);
            cartManager.add(product);
        }
        return cartManager.numberOfProducts();
    }
}
