package com.kozhukhar.carshop;

import com.kozhukhar.carshop.command.CarShopCommandFactory;
import com.kozhukhar.carshop.command.Command;
import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop.context.StorageContext;
import com.kozhukhar.carshop.entity.Car;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CartService;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.carshop.storage.PathStore;
import com.kozhukhar.carshop.util.LocaleMessageUtil;
import com.kozhukhar.carshop.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class ConsoleShopApp {

    private static final Logger LOG = Logger.getLogger(ConsoleShopApp.class);

    public static void main(String[] args) {
        LOG.debug("Application was started.");
        init();
    }

    private static void init() {
        System.out.println(ConsoleView.MAIN_PAGE);

        try {
            LocaleMessageUtil localeMessageUtil = new LocaleMessageUtil();
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setLocaleMessageUtil(localeMessageUtil);
            StorageContext storageContext = new StorageContext();
            initializingContext(serviceContext, storageContext);
            CarShopCommandFactory carShopCommandFactory = new CarShopCommandFactory(serviceContext);
            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    String inputCommand = scanner.nextLine();
                    Command command = carShopCommandFactory.getCommand(inputCommand);
                    System.out.println(command.execute());
                } catch (AppException e) {
                    LOG.error(serviceContext.getLocaleMessageUtil().localizeMessage(
                            e.getMessage()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }

    public static void initializingContext(ServiceContext serviceContext, StorageContext storageContext) throws IOException, ClassNotFoundException, AppException {
        CatalogStore catalogStore = initCatalogFromFile();
        if (catalogStore == null) {
            catalogStore = initializingCatalog();
        }
        storageContext.setCatalogStore(catalogStore);
        serviceContext.setCatalogService(new CatalogService(
                storageContext.getCatalogStore(),
                storageContext.getTransportMap(),
                initMode()
                ));
        serviceContext.setCartService(new CartService(
                storageContext.getLastFiveProductStore(),
                storageContext.getOrderStore(),
                storageContext.getCartStore(),
                storageContext.getCatalogStore()
        ));
    }


    private static CatalogStore initCatalogFromFile() throws IOException, ClassNotFoundException, AppException {
        CatalogStore catalogStore = null;

        try (InputStream fileIn = new FileInputStream(PathStore.CATALOG_FILE);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {

            catalogStore = (CatalogStore) objectIn.readObject();
        } catch (Exception ex) {
            return initializingCatalog();
        }
        return catalogStore;
    }

    private static CatalogStore initializingCatalog() throws AppException {
        CatalogStore catalogStore = new CatalogStore();
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("Audi", "Q3 Sportback"), 250, 100000)
        );
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("Mercedes", "AMG E-43"), 260, 105000)
        );
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("BMW", "X5"), 240, 90000)
        );
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("t1", "tm1"), 0, 10)
        );
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("t2", "tm2"), 0, 10)
        );
        catalogStore.addProductToCatalog(new Car(
                new TransportEmbedded("t3", "tm3"), 0, 10)
        );
        return catalogStore;
    }

    private static Boolean initMode() {
        boolean mode = false;

        System.out.println("Input addition mode for generation :");
        System.out.println("1 - Auto generation;");
        System.out.println("2 - Manually.");
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 2) {
            mode = true;
        }
        System.out.println("Mode was changed!");
        return mode;
    }

}
