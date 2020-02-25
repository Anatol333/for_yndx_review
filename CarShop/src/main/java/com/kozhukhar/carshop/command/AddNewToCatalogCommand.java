package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.view.ConsoleView;

public class AddNewToCatalogCommand implements Command {

        private String transportInfo;

        private CatalogService catalogService;

        private CreatorAnnotation creatorAnnotation;

    public AddNewToCatalogCommand(CatalogService catalogService, String secondCommand, CreatorAnnotation creatorAnnotation) {
        transportInfo = secondCommand;
        this.catalogService = catalogService;
        this.creatorAnnotation = creatorAnnotation;
    }

    @Override
    public String execute() throws AppException {
        System.out.println(ConsoleView.ADD_INFO);
        catalogService.addNewTransport(transportInfo, creatorAnnotation);
        return catalogService.showCatalogInfo();
    }
}
