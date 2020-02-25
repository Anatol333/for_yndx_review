package com.kozhukhar.carshop.storage;

import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.util.LinkedHashMap;
import java.util.Map;

public class LastFiveProductStore {

    private static final Integer MAX_SIZE = 5;

    private LinkedHashMap<TransportEmbedded, Integer> lastFiveProduct;

    public LastFiveProductStore() {
        lastFiveProduct = new LinkedHashMap<TransportEmbedded, Integer>() {
            protected boolean removeEldestEntry(Map.Entry<TransportEmbedded, Integer> eldest) {
                return size() > MAX_SIZE;
            }
        };
    }

    public void addNewProduct(CartEntry cartEntry) throws AppException {
        try {
            lastFiveProduct.put((TransportEmbedded) cartEntry.getObjectOfProduct(), cartEntry.getCountOfProduct());
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_BE_ADDED_ELEMENT_TO_LAST_FIVE);
        }
    }

    public LinkedHashMap<TransportEmbedded, Integer> getLastFiveProduct() {
        return lastFiveProduct;
    }
}
