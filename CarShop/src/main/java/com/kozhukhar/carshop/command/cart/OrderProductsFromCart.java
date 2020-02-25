package com.kozhukhar.carshop.command.cart;

import com.kozhukhar.carshop.command.Command;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;

public class OrderProductsFromCart implements Command {

    private CartService cartService;

    public OrderProductsFromCart(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute() throws AppException {
        Integer fullPrice = cartService.orderProductsFromCart();
        return "Congratulation, Products was Ordered! Purchase price is " + fullPrice;
    }
}
