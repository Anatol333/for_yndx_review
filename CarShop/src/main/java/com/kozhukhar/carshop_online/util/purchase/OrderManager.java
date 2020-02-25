package com.kozhukhar.carshop_online.util.purchase;

import com.kozhukhar.carshop_online.db.dto.Order;
import com.kozhukhar.carshop_online.db.dto.OrderedProduct;
import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import com.kozhukhar.carshop_online.web.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {

    private Order order;

    public OrderManager() {
        order = new Order();
    }

    public void orderInit(CartManager cartManager, Integer userId) throws AppException {
        order.setStatus(OrderStatus.FORMED.getName());
        order.setMessage(OrderStatusMessages.FORMED_MESSAGE);
        order.setProducts(getProducts(cartManager.findAll()));
        order.setUserID(userId);
        order.setPrice(cartManager.getFullPrice());
    }

    public void confirm() {
        order.setStatus(OrderStatus.CONFIRMED.getName());
        order.setMessage(OrderStatusMessages.CONFIRMED_MESSAGE);
    }

    private List<OrderedProduct> getProducts(Map<Product, Integer> cartProducts) throws AppException {
        List<OrderedProduct> products = new ArrayList<>();
        if (cartProducts.size() == 0) {
            throw new AppException(Messages.CART_IS_EMPTY_ORDER);
        }
        cartProducts.forEach((k, v) ->
                products.add(new OrderedProduct(k.getId(), k.getName(), v, k.getPrice()))
        );
        return products;
    }

    public Order info() {
        return order;
    }

    public void order(OrderService orderService) throws AppException {
        order.setDateTime(LocalDateTime.now());
        orderService.saveOrder(order);
    }

    public void cancel(String message) {
        order.setStatus(OrderStatus.CANCELED.getName());
        order.setMessage(message);
    }
}
