package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.ProductDao;
import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.db.transact.ConnectionHolder;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.DBUtil;
import com.kozhukhar.carshop_online.util.ResultSetConverter;
import com.kozhukhar.carshop_online.web.resource_tag.FieldTags;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao<Product> {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

    private ResultSetConverter converter;

    public ProductDaoImpl() {
        this.dbUtil = new DBUtil();
        this.converter = new ResultSetConverter();
    }

    @Override
    public List<Product> getPage(int pageNum, int pageLength) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_GET_PRODUCTS_BY_PAGE_NUM);

            int atr = 1;
            pstmt.setInt(atr++, pageNum * pageLength);
            pstmt.setInt(atr, pageLength);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(FieldTags.ID));
                product.setName(rs.getString(FieldTags.NAME));
                product.setPrice(rs.getFloat(FieldTags.PRICE));
                product.setCategory(rs.getString(FieldTags.CATEGORY_NAME));
                product.setProducer(rs.getString(FieldTags.PRODUCER_NAME));
                products.add(product);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_PRODUCTS, ex);
        } finally {
            dbUtil.close(pstmt);
        }

        return products;
    }

    @Override
    public List<Product> getAll() throws DBException {
        return getAllByQuery(Queries.SQL_GET_ALL_PRODUCTS);
    }

    @Override
    public List<Product> getAllByQuery(String query) throws DBException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();

        try {
            conn = ConnectionHolder.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(FieldTags.ID));
                product.setName(rs.getString(FieldTags.NAME));
                product.setPrice(rs.getFloat(FieldTags.PRICE));
                products.add(product);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_PRODUCTS, ex);
        } finally {
            dbUtil.close(stmt);
        }

        return products;
    }

    @Override
    public Product get(Integer id) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product newProduct = null;

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_GET_PRODUCTS_BY_ID);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                converter = new ResultSetConverter();
                newProduct = new Product();
                converter.initProductField(rs, newProduct);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_PRODUCT, ex);
        } finally {
            dbUtil.close(pstmt);
        }

        return newProduct;
    }

}
