package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;

public class CountOfProductsCommand implements ServerCommand {

    private CatalogService catalogService;

    public CountOfProductsCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String execute(String parameters) throws AppException {
        return String.valueOf(
                catalogService.getCatalogStore().getFullProductInfo().size()
        );
    }

}
