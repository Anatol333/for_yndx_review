package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;

public class ShowAllPurchaseHistory implements Command {

    private CartService cartService;

    private String date;

    public ShowAllPurchaseHistory(String date, CartService cartService) {
        this.cartService = cartService;
        this.date = date;
    }

    @Override
    public String execute() throws AppException {
        return cartService.showOrderInfo(date);
    }
}
