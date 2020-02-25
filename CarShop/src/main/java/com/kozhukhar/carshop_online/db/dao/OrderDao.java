package com.kozhukhar.carshop_online.db.dao;

import com.kozhukhar.carshop_online.db.dto.Order;
import com.kozhukhar.carshop_online.db.dto.OrderedProduct;
import com.kozhukhar.carshop_online.exception.DBException;

import java.util.List;

public interface OrderDao {

    void add(Order order) throws DBException;

    void addProducts(Integer idOrder, OrderedProduct product) throws DBException;

    List<Order> getAll() throws DBException;
}
