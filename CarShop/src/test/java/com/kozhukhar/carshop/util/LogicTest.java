package com.kozhukhar.carshop.util;

import com.kozhukhar.carshop.Examples;
import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop.context.StorageContext;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogicTest {

    private CatalogStore catalog;
    private CartStore cart;
    private ServiceContext serviceContext;
    private StorageContext storageContext;

    @Before
    public void setUp() throws Exception {
        serviceContext = new ServiceContext();
        storageContext = new StorageContext();
        serviceContext.setCartService(new CartService(
                storageContext.getLastFiveProductStore(),
                storageContext.getOrderStore(),
                storageContext.getCartStore(),
                storageContext.getCatalogStore()
        ));
        catalog = storageContext.getCatalogStore();
        cart = storageContext.getCartStore();
    }

    @Test
    public void addElementToCatalog() throws AppException {
        assertEquals(0, catalog.getFullProductInfo().size());
        catalog.addProductToCatalog(Examples.TRANSPORTS[0]);
        assertEquals(1, catalog.getFullProductInfo().size());
    }

    @Test
    public void addElementToCart() throws AppException {
        CartEntry cartEntry = new CartEntry(Examples.TRANSPORTS[0].getKey(), null);
        cart.addProductToCatalog(cartEntry);
        assertEquals(1,cart.getCommonProductInfo().size());
    }
}
