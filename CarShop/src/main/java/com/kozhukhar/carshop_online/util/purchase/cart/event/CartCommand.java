package com.kozhukhar.carshop_online.util.purchase.cart.event;

import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import com.kozhukhar.carshop_online.web.service.ProductService;

public interface CartCommand {

    Integer execute(ProductService productServiceImpl, Integer productId, CartManager cartManager);

}
