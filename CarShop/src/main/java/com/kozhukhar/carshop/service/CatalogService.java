package com.kozhukhar.carshop.service;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop.creator.TransportAddition;
import com.kozhukhar.carshop.creator.type.TransportMap;
import com.kozhukhar.carshop.dao.impl.TransportDaoImpl;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.carshop.view.ConsoleView;
import com.kozhukhar.carshop.view.DrawTable;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class CatalogService {

    private CatalogStore catalogStore;

    private TransportMap transportMap;

    private Boolean modeGeneration;

    private static final String SEP = " | ";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";

    public CatalogService() {
    }

    public CatalogService(CatalogStore catalogStore, TransportMap transportMap, Boolean modeGeneration) {
        this.catalogStore = catalogStore;
        this.transportMap = transportMap;
        this.modeGeneration = modeGeneration;
    }

    private static final Logger LOG = Logger.getLogger(CatalogService.class);

    public String showCatalogInfo() {
        LOG.debug("Showing catalog in service.");
        TransportDaoImpl transportDaoImpl = new TransportDaoImpl(catalogStore);
        String catalogProduct = null;
        try {
            catalogProduct = DrawTable.drawProductTable(transportDaoImpl.getAll());
        } catch (DBException e) {
            e.printStackTrace();
        }
        return catalogProduct;
    }

    public JSONObject getJsonProductByName(String name) throws AppException {
        JSONObject productDetails = new JSONObject();
        TransportDaoImpl transportDaoImpl = new TransportDaoImpl(catalogStore);
        try {
            CartEntry entry = (CartEntry) transportDaoImpl.get(name);
            TransportEmbedded transport = (TransportEmbedded) entry.getObjectOfProduct();
            if (transport != null) {
                productDetails.put(PRODUCT_PRICE,  entry.getPrice());
                productDetails.put(PRODUCT_NAME, transport);
            }
        } catch (DBException ex) {
            throw new AppException(Messages.CANNOT_GET_PRODUCT_BY_NAME);
        }
        return productDetails;
    }

    public String getProductByName(String name) throws AppException {
        TransportDaoImpl transportDaoImpl = new TransportDaoImpl(catalogStore);
        String productOutput = Messages.PRODUCT_WAS_NOT_FOUND_BY_NAME + name;
        try {
            CartEntry entry = (CartEntry) transportDaoImpl.get(name);
            TransportEmbedded transport = (TransportEmbedded) entry.getObjectOfProduct();
            if (transport != null) {
                productOutput = transport + SEP + entry.getPrice();
            }
        } catch (DBException ex) {
            throw new AppException(Messages.CANNOT_GET_PRODUCT_BY_NAME);
        }
        return productOutput;
    }

    public void addNewTransport(String transportInfo, CreatorAnnotation creatorAnnotation) throws AppException {
        try {
            Integer number = Integer.valueOf(String.valueOf(transportInfo.charAt(0)));
            TransportAddition transportAddition = transportMap.getTransportByNumber(number);
            Transport transport = null;
            while (transport == null || catalogStore.getFullProductInfo().containsKey(transport.getKey())) {
                transport = (Transport) transportAddition.createTransport(transportInfo, modeGeneration, creatorAnnotation);
            }
            catalogStore.addProductToCatalog(transport);
        } catch (Exception ex) {
            throw new AppException(Messages.TRANSPORT_WAS_NOT_ADDED + ex.getMessage());
        }
    }

    public void addNewTransportByReflection(CreatorAnnotation creatorAnnotation) throws AppException {
        try {
            System.out.println("Choose type of car: ");
            System.out.println(ConsoleView.ADD_INFO);

            Scanner scanner = new Scanner(System.in);
            Integer type = Integer.valueOf(scanner.nextLine());

            TransportAddition transportAddition = transportMap.getTransportByNumber(type);
            Transport transport = (Transport) transportAddition.createTransport(null, modeGeneration, creatorAnnotation);
            catalogStore.addProductToCatalog(transport);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AppException(Messages.TRANSPORT_WAS_NOT_ADDED);
        }
    }

    public CatalogStore getCatalogStore() {
        return catalogStore;
    }
}
