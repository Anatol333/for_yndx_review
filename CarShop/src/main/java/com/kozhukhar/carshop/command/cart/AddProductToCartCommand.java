package com.kozhukhar.carshop.command.cart;

import com.kozhukhar.carshop.command.Command;
import com.kozhukhar.carshop.command.ExitFromApplicationCommand;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;
import org.apache.log4j.Logger;

public class AddProductToCartCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ExitFromApplicationCommand.class);

    private String carName;

    private CartService cartService;

    public AddProductToCartCommand(String carName, CartService cartService) {
        this.carName = carName;
        this.cartService = cartService;
    }

    @Override
    public String execute() throws AppException {
        LOG.debug("Adding product to cart");
        cartService.addProductToCart(carName);
        return cartService.showCartInfo();
    }
}
