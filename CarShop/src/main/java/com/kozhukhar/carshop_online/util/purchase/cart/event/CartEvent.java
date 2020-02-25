package com.kozhukhar.carshop_online.util.purchase.cart.event;

import com.kozhukhar.carshop_online.web.resource_tag.FieldTags;

import java.util.HashMap;
import java.util.Map;

public class CartEvent {

    private Map<String, CartCommand> cartCommandMap;

    public CartEvent() {
        this.cartCommandMap = new HashMap<>();
        initCartCommand();
    }

    private void initCartCommand() {
        cartCommandMap.put(FieldTags.CART_ADD, new AddToCartCommand());
        cartCommandMap.put(FieldTags.CART_REMOVE, new RemoveFromCartCommand());
        cartCommandMap.put(FieldTags.CART_MINUS, new MinusToCartCommand());
        cartCommandMap.put(FieldTags.CART_PLUS, new PlusToCartCommand());
    }

    public CartCommand getCommand(String strCommand) {
        CartCommand command = null;
        if (strCommand != null) {
            command = cartCommandMap.get(strCommand);
        }
        return command;
    }


}
