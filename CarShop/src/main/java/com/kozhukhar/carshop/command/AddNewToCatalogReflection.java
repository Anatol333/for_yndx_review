package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;

public class AddNewToCatalogReflection implements Command {

    private CatalogService catalogService;

    private CreatorAnnotation creatorAnnotation;

    public AddNewToCatalogReflection(CatalogService catalogService, CreatorAnnotation creatorAnnotation) {
        this.catalogService = catalogService;
        this.creatorAnnotation = creatorAnnotation;
    }

    @Override
    public String execute() throws AppException {
        catalogService.addNewTransportByReflection(creatorAnnotation);
        return catalogService.showCatalogInfo();
    }
}
