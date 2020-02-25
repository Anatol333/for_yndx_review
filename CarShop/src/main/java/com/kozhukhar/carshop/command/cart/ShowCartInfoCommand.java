package com.kozhukhar.carshop.command.cart;

import com.kozhukhar.carshop.command.Command;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;

public class ShowCartInfoCommand implements Command {

    private CartService cartService;

    public ShowCartInfoCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute() throws AppException {
        return cartService.showCartInfo();
    }
}
