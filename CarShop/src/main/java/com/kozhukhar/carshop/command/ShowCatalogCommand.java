package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.view.InterfaceLib;
import org.apache.log4j.Logger;

public class ShowCatalogCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ShowCatalogCommand.class);

    private CatalogService catalogService;


    public ShowCatalogCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String execute() {
        LOG.debug("Showing all product from catalog.");
        return catalogService.showCatalogInfo();
    }
}
