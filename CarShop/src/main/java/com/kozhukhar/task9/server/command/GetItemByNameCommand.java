package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;

public class GetItemByNameCommand implements ServerCommand {

    private CatalogService catalogService;

    public GetItemByNameCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String execute(String parameters) throws AppException {
        return catalogService.getProductByName(parameters);
    }
}
