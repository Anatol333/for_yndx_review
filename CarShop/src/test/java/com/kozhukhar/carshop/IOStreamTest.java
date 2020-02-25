package com.kozhukhar.carshop;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop.context.StorageContext;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.*;

public class IOStreamTest {

    private static final String FILE_OUT_TEST_GZ = "catalog_test.gz";
    private CatalogStore catalog;
    private CartStore cart;
    private ServiceContext serviceContext;
    private StorageContext storageContext;
    private static final String FILE_OUT_TEST = "catalog_test.txt";

    @Before
    public void setUp() throws IOException, AppException {
        serviceContext = new ServiceContext();
        storageContext = new StorageContext();
        CatalogStore catalogStore = new CatalogStore();
        for (Transport transport : Examples.TRANSPORTS) {
            catalogStore.addProductToCatalog(transport);
        }
        storageContext.setCatalogStore(catalogStore);

        catalog = storageContext.getCatalogStore();
        cart = storageContext.getCartStore();
        try (OutputStream outputStream = new FileOutputStream(FILE_OUT_TEST);
             ObjectOutputStream objectOut = new ObjectOutputStream(outputStream)) {
            objectOut.writeObject(storageContext.getCatalogStore());
        }
    }

    @Test
    public void inputStreamReadCatalogTest() throws IOException, ClassNotFoundException {
        CatalogStore catalogStore = null;
        try (InputStream inputStream = new FileInputStream(FILE_OUT_TEST);
             ObjectInputStream objectIn = new ObjectInputStream(inputStream)) {
            catalogStore = (CatalogStore) objectIn.readObject();
        }
        assertEquals(Examples.TRANSPORTS.length, catalogStore.getFullProductInfo().size());
    }

    @Test
    public void outputStreamToGZipFormat() throws AppException, IOException {
        byte[] buffer = new byte[1024];
        GZIPOutputStream os = new GZIPOutputStream(new FileOutputStream(FILE_OUT_TEST_GZ));
        FileInputStream in = new FileInputStream(FILE_OUT_TEST);

        int totalSize;
        while ((totalSize = in.read(buffer)) > 0) {
            os.write(buffer, 0, totalSize);
        }
        File fileTest = new File(FILE_OUT_TEST);
        File fileTestGz = new File(FILE_OUT_TEST_GZ);
        
        assertTrue(fileTest.length() > fileTestGz.length());
    }
}
