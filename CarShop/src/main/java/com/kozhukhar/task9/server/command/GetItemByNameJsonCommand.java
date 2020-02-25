package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;

public class GetItemByNameJsonCommand implements ServerCommand {

    private CatalogService catalogService;

    public GetItemByNameJsonCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String execute(String parameters) throws AppException {
        return catalogService.getJsonProductByName(parameters).toString();
    }
}
