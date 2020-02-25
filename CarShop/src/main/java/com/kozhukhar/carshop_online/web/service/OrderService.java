package com.kozhukhar.carshop_online.web.service;

import com.kozhukhar.carshop_online.db.dto.Order;
import com.kozhukhar.carshop_online.exception.AppException;

public interface OrderService {

    void saveOrder(Order order) throws AppException;

}
