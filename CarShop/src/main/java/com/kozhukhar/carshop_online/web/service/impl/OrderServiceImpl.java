package com.kozhukhar.carshop_online.web.service.impl;

import com.kozhukhar.carshop_online.db.dao.OrderDao;
import com.kozhukhar.carshop_online.db.dao.impl.OrderDaoImpl;
import com.kozhukhar.carshop_online.db.dto.Order;
import com.kozhukhar.carshop_online.db.dto.OrderedProduct;
import com.kozhukhar.carshop_online.db.transact.JdbcTransactionManager;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.web.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    private JdbcTransactionManager transactionManager;

    public OrderServiceImpl(JdbcTransactionManager transactionManager, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void saveOrder(Order order) throws AppException {
        transactionManager.execute(() -> {
            orderDao.add(order);
            Integer orderID = orderDao.getAll().size();
            for (OrderedProduct product : order.getProducts()) {
                orderDao.addProducts(orderID, product);
            }
            return true;
        });
    }

}
