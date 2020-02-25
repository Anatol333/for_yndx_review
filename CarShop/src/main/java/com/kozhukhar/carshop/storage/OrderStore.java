package com.kozhukhar.carshop.storage;

import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.bean.OrderBean;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.util.LocalDateUtil;

import java.time.LocalDateTime;
import java.util.*;

public class OrderStore {

    private TreeMap<LocalDateTime, OrderBean> orderHistory;

    public OrderStore() {
        orderHistory = new TreeMap<>();
    }

    public Integer addNewOrder(List<CartEntry> productsList) {
        OrderBean orderBean = new OrderBean();
        Integer fullPrice = 0;
        for (CartEntry cartEntry : productsList) {
            fullPrice += cartEntry.getPrice() * cartEntry.getCountOfProduct();
        }
        orderBean.setFullOrderPrice(fullPrice);
        orderBean.setCartEntry(productsList);
        orderHistory.put(
                LocalDateTime.now(),
                orderBean
        );
        return fullPrice;
    }

    public TreeMap<LocalDateTime, OrderBean> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(TreeMap<LocalDateTime, OrderBean> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public TreeMap<LocalDateTime, OrderBean> getOrderHistoryByDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        TreeMap<LocalDateTime, OrderBean> filteredMap = new TreeMap<>();
        orderHistory.forEach((k, v) -> {
            if (k.compareTo(dateTimeStart) >= 0 && k.compareTo(dateTimeEnd) <= 0) {
                filteredMap.put(k, v);
            }
        });
        return filteredMap;
    }

    public TreeMap<LocalDateTime, OrderBean> getOrderHistoryByDateOne(LocalDateTime dateTime) throws AppException {
        TreeMap<LocalDateTime, OrderBean> filteredMap = new TreeMap<>();
        LocalDateTime currentDate = getDateNearest(orderHistory.keySet(), dateTime);
        filteredMap.put(currentDate, orderHistory.get(getDateNearest(orderHistory.keySet(), dateTime)));
        return filteredMap;
    }

    private LocalDateTime getDateNearest(Set<LocalDateTime> dates, LocalDateTime targetDate) throws AppException {
        NavigableSet<LocalDateTime> setDates = new TreeSet<>(dates);
        LocalDateTime lowerDate = setDates.lower(targetDate);
        LocalDateTime higherDate = setDates.higher(targetDate);

        return LocalDateUtil.dateCompare(lowerDate, higherDate);
    }
}
