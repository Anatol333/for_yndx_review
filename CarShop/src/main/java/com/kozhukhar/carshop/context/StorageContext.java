package com.kozhukhar.carshop.context;

import com.kozhukhar.carshop.creator.type.TransportMap;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.carshop.storage.LastFiveProductStore;
import com.kozhukhar.carshop.storage.OrderStore;

public class StorageContext {

    private CartStore cartStore;

    private CatalogStore catalogStore;

    private TransportMap transportMap;

    private LastFiveProductStore lastFiveProductStore;

    private OrderStore orderStore;

    public StorageContext() {
        cartStore = new CartStore();
        catalogStore = new CatalogStore();
        lastFiveProductStore = new LastFiveProductStore();
        orderStore = new OrderStore();
        transportMap = new TransportMap();
    }

    public StorageContext(CartStore cartStore, CatalogStore catalogStore, LastFiveProductStore lastFiveProductStore, OrderStore orderStore) {
        this.cartStore = cartStore;
        this.catalogStore = catalogStore;
        this.lastFiveProductStore = lastFiveProductStore;
        this.orderStore = orderStore;
    }

    public TransportMap getTransportMap() {
        return transportMap;
    }

    public void setTransportMap(TransportMap transportMap) {
        this.transportMap = transportMap;
    }

    public CartStore getCartStore() {
        return cartStore;
    }

    public void setCartStore(CartStore cartStore) {
        this.cartStore = cartStore;
    }

    public CatalogStore getCatalogStore() {
        return catalogStore;
    }

    public void setCatalogStore(CatalogStore catalogStore) {
        this.catalogStore = catalogStore;
    }

    public LastFiveProductStore getLastFiveProductStore() {
        return lastFiveProductStore;
    }

    public void setLastFiveProductStore(LastFiveProductStore lastFiveProductStore) {
        this.lastFiveProductStore = lastFiveProductStore;
    }

    public OrderStore getOrderStore() {
        return orderStore;
    }

    public void setOrderStore(OrderStore orderStore) {
        this.orderStore = orderStore;
    }
}
