package com.kozhukhar.carshop.dao.impl;

import com.kozhukhar.carshop.dao.CartEntryDao;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;

import java.util.ArrayList;
import java.util.List;

public class CartEntryDaoImpl implements CartEntryDao {

    private CartStore cartStore;
    private CatalogStore catalogStore;

    public CartEntryDaoImpl(CartStore cartStore, CatalogStore catalogStore) {
        this.cartStore = cartStore;
        this.catalogStore = catalogStore;
    }

    @Override
    public List getAll() throws DBException {
        List<CartEntry> cartEntries = new ArrayList<>();
        try {
            cartStore.getCommonProductInfo()
                    .forEach((k, v) -> cartEntries.add(new CartEntry(k, v)));
            TransportDaoImpl transportDaoImpl = new TransportDaoImpl(catalogStore);
            List<Transport> fullProductInfo = transportDaoImpl.getAll();
            for (CartEntry cartEntry : cartEntries) {
                fullProductInfo.forEach(elem -> {
                    if (elem.getKey().equals(cartEntry.getObjectOfProduct())) {
                        cartEntry.setPrice(elem.getPrice());
                    }
                });
            }
        } catch (Exception ex) {
            throw new DBException(Messages.CANNOT_GET_ALL_PRODUCTS_FROM_CATALOG, ex);
        }
        return cartEntries;
    }

    @Override
    public void save(Object o) throws DBException {
        try {
            if (o == null) throw new AppException();
            if (!(o instanceof CartEntry)) throw new AppException();

            cartStore.addProductToCatalog((CartEntry) o);
        } catch (AppException ex) {
            throw new DBException(Messages.CANNOT_SAVE_NEW_OBJECT_ITS_NULL, ex);
        }
    }

    public void deleteAll() {
        cartStore.clear();
    }
}
