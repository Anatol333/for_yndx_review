package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.OrderDao;
import com.kozhukhar.carshop_online.db.dto.Order;
import com.kozhukhar.carshop_online.db.dto.OrderedProduct;
import com.kozhukhar.carshop_online.db.transact.ConnectionHolder;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.DBUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.ID;

public class OrderDaoImpl implements OrderDao {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public List<Order> getAll() throws DBException {
        Statement stmt = null;
        List<Order> orderList = new ArrayList<>();
        try {
            Connection conn = ConnectionHolder.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.SQL_GET_ALL_ORDERS);
            while (rs.next()) {
                Order newOrder = new Order();
                newOrder.setId(rs.getInt(ID));
                orderList.add(newOrder);
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_ORDER, ex);
        } finally {
            dbUtil.close(stmt);
        }
        return orderList;
    }

    @Override
    public void add(Order order) throws DBException {
        PreparedStatement pstmt = null;
        try {

            Connection conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_SAVE_NEW_ORDER);
            int atr = 1;
            pstmt.setString(atr++, order.getStatus());
            pstmt.setString(atr++, order.getMessage());
            pstmt.setString(atr++, order.getDateTime().toString());
            pstmt.setInt(atr++, order.getUserID());
            pstmt.setString(atr, order.getPayType());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.ORDER_NOT_ADD, ex);
        } finally {
            dbUtil.close(pstmt);
        }
    }

    @Override
    public void addProducts(Integer idOrder, OrderedProduct product) throws DBException {
        PreparedStatement pstmt = null;
        try {
            Connection conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_SAVE_ORDER_PRODUCTS);
            int atr = 1;
            pstmt.setInt(atr++, idOrder);
            pstmt.setInt(atr++, product.getProductId());
            pstmt.setInt(atr++, product.getNumbers());
            pstmt.setFloat(atr, product.getPrice());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.PRODUCT_LIST_WAS_NOT_ORDERED, ex);
        } finally {
            dbUtil.close(pstmt);
        }
    }
}
