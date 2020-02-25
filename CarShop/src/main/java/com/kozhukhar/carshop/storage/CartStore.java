package com.kozhukhar.carshop.storage;

import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.AppException;

import java.util.HashMap;
import java.util.Map;

public class CartStore {

    private Map<TransportEmbedded, Integer> commonProductInfo;

    public CartStore() {
        commonProductInfo = new HashMap<>();
    }

    public void addProductToCatalog(CartEntry cartEntry) throws AppException {
        try {
            TransportEmbedded productKey = (TransportEmbedded) cartEntry.getObjectOfProduct();
            if (!commonProductInfo.containsKey(productKey)) {
                commonProductInfo.put(productKey, 1);
            } else {
                commonProductInfo.put(productKey, commonProductInfo.get(productKey) + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AppException("Product cannot be added to the cart!");
        }
    }

    public Map<TransportEmbedded, Integer> getCommonProductInfo() {
        return commonProductInfo;
    }

    public void setCommonProductInfo(Map<TransportEmbedded, Integer> commonProductInfo) {
        this.commonProductInfo = commonProductInfo;
    }

    public void clear() {
        commonProductInfo = new HashMap<>();
    }
}
