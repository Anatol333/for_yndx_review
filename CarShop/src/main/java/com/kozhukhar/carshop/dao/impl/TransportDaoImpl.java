package com.kozhukhar.carshop.dao.impl;

import com.kozhukhar.carshop.dao.TransportDao;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.storage.CatalogStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransportDaoImpl implements TransportDao {

    private CatalogStore catalogStore;

    public TransportDaoImpl(CatalogStore catalogStore) {
        this.catalogStore = catalogStore;
    }

    @Override
    public Object get(String name) throws DBException {
        CartEntry cartEntry = new CartEntry();
        try {
            for (Map.Entry<TransportEmbedded, Transport> entry : catalogStore.getFullProductInfo().entrySet()) {
                if ((entry.getKey().getName() + " " + entry.getKey().getModel()).equals(name)) {
                    cartEntry.setObjectOfProduct(new TransportEmbedded(
                            entry.getKey().getName(), entry.getKey().getModel()
                    ));
                    cartEntry.setPrice(entry.getValue().getPrice());
                }
            }
        } catch (Exception ex) {
            throw new DBException(Messages.CANNOT_GET_PRODUCT_BY_NAME, ex);
        }
        return cartEntry;
    }

    @Override
    public List getAll() throws DBException {
        List<Transport> transports = new ArrayList<>();
        try {
            catalogStore.getFullProductInfo()
                    .forEach((k, v) -> transports.add(v));
        } catch (Exception ex) {
            throw new DBException(Messages.CANNOT_GET_ALL_PRODUCTS_FROM_CATALOG, ex);
        }
        return transports;
    }
}
