package com.kozhukhar.carshop.storage;

import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CatalogStore implements Serializable {

    private Map<TransportEmbedded, Transport> fullProductInfo;

    public CatalogStore() {
        fullProductInfo = new HashMap<>();
    }

    public void addProductToCatalog(Transport transport) throws AppException {
        if (null == transport.getKey())
            throw new AppException(Messages.ERROR_ADDING_NEW_PRODUCT_TO_CATALOG);
        TransportEmbedded keyProduct = transport.getKey();
        fullProductInfo.put(keyProduct, transport);
    }

    public Map<TransportEmbedded, Transport> getFullProductInfo() {
        return fullProductInfo;
    }

    public void setFullProductInfo(Map<TransportEmbedded, Transport> fullProductInfo) {
        this.fullProductInfo = fullProductInfo;
    }
}
