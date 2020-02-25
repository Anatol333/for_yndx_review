package com.kozhukhar.carshop_online.web.service.impl;

import com.kozhukhar.carshop_online.db.dao.ProductDao;
import com.kozhukhar.carshop_online.db.dao.impl.ProductDaoImpl;
import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.db.transact.JdbcTransactionManager;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.web.service.ProductService;

import javax.servlet.ServletContext;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.PRODUCT_DAO;
import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.TRANSACTION_MNG;

public class ProductServiceImpl implements ProductService {

    private JdbcTransactionManager transactionManager;

    private ProductDao productDao;

    public ProductServiceImpl(JdbcTransactionManager transactionManager, ProductDao productDao) {
        this.transactionManager = transactionManager;
        this.productDao = productDao;
    }

    @Override
    public Product getProductById(Integer productId) {
        Product product = null;
        try {
            product = transactionManager.execute(() -> productDao.get(productId));
        } catch (AppException e) {
            e.printStackTrace();
        }
        return product;
    }
}
