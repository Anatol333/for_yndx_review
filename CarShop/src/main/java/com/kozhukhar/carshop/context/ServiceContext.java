package com.kozhukhar.carshop.context;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop.creator.TransportAdditionImpl;
import com.kozhukhar.carshop.service.CartService;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.util.LocaleMessageUtil;
import com.kozhukhar.carshop.view.InterfaceLib;

public class ServiceContext {

    private CartService cartService;

    private CatalogService catalogService;

    private CreatorAnnotation creatorAnnotation;

    private LocaleMessageUtil localeMessageUtil;

    public ServiceContext() {
        cartService = new CartService();
        catalogService = new CatalogService();
        creatorAnnotation = new CreatorAnnotation();
        localeMessageUtil = new LocaleMessageUtil();
    }

    public ServiceContext(CartService cartService, CatalogService catalogService) {
        this.cartService = cartService;
        this.catalogService = catalogService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public LocaleMessageUtil getLocaleMessageUtil() {
        return localeMessageUtil;
    }

    public void setLocaleMessageUtil(LocaleMessageUtil localeMessageUtil) {
        this.localeMessageUtil = localeMessageUtil;
    }

    public CreatorAnnotation getCreatorAnnotation() {
        return creatorAnnotation;
    }

    public void setCreatorAnnotation(CreatorAnnotation creatorAnnotation) {
        this.creatorAnnotation = creatorAnnotation;
    }
}
