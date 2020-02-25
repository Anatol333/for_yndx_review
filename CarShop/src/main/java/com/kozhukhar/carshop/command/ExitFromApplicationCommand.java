package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.storage.PathStore;
import org.apache.log4j.Logger;

import java.io.*;

public class ExitFromApplicationCommand implements Command {

    private CatalogService catalogService;

    private String countForSave;

    private static final Logger LOG = Logger.getLogger(ExitFromApplicationCommand.class);

    public ExitFromApplicationCommand(CatalogService catalogService, String secondParam) {
        this.catalogService = catalogService;
        countForSave = secondParam;
    }

    @Override
    public String execute() {
        LOG.trace("Saving store to file...");
        try (OutputStream outputStream = new FileOutputStream(PathStore.CATALOG_FILE);
             ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);) {
            int count = 1;
            if (countForSave != null && Integer.parseInt(countForSave) > 0) {
                count = Integer.parseInt(countForSave);
            }
            for (int i = 0; i < count; ++i) {
                objectOut.writeObject(catalogService.getCatalogStore());
            }
        } catch (IOException ex) {
            LOG.trace(Messages.CHANGES_CANNOT_BE_SAVED, ex);
        }
        LOG.debug("Application closed");
        System.exit(0);
        return "";
    }
}
