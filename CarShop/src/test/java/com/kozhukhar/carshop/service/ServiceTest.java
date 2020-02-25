package com.kozhukhar.carshop.service;

import com.kozhukhar.carshop.Examples;
import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop.context.StorageContext;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.carshop.storage.LastFiveProductStore;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceTest {

    private static final long MAX_SIZE_LAST = 5;
    private CartService cartService;
    private CartStore cart;
    private CatalogStore catalog;
    private LastFiveProductStore lastFiveProductStore;

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
        cartService = serviceContext.getCartService();
        catalog = storageContext.getCatalogStore();
        lastFiveProductStore = storageContext.getLastFiveProductStore();
    }

    @Test(expected = AppException.class)
    public void addElementToCart() throws AppException {
        CartEntry cartEntry = new CartEntry(Examples.TRANSPORTS[0].getKey(), null);
        cartService.addProductToCart(cartEntry.toString());
    }

    @Test
    public void showLastFiveInfo() throws AppException {
        for (Transport transport : Examples.TRANSPORTS) {
            catalog.addProductToCatalog(transport);
        }
        for (Transport transport : Examples.TRANSPORTS) {
            cartService.addProductToCart(transport.getKey().toString());
        }
        assertEquals(MAX_SIZE_LAST, lastFiveProductStore.getLastFiveProduct().size());
        List<Object> transport = Arrays.asList(lastFiveProductStore.getLastFiveProduct().keySet().toArray());
        assertEquals(Examples.TRANSPORTS[Examples.TRANSPORTS.length - 1].getKey(), transport.get(transport.size()-1));
    }
}