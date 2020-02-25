package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;

public class ShowLastFiveCommand implements Command {

    private CartService cartService;

    public ShowLastFiveCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String execute() throws AppException {
        return cartService.showLastFiveInfo();
    }
}
