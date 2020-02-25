package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop.service.CatalogService;
import org.json.simple.JSONObject;

public class CountOfProductsJsonCommand implements ServerCommand {

    private CatalogService catalogService;

    private static final String COUNT = "count";

    public CountOfProductsJsonCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String execute(String parameters) {
        JSONObject productDetails = new JSONObject();
        productDetails.put(COUNT, catalogService.getCatalogStore().getFullProductInfo().size());
        return productDetails.toString();
    }
}
