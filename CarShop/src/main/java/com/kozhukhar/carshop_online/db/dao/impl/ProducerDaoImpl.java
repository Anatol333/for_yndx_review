package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.ProducerDao;
import com.kozhukhar.carshop_online.db.dto.Producer;
import com.kozhukhar.carshop_online.db.transact.ConnectionHolder;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.DBUtil;
import com.kozhukhar.carshop_online.web.resource_tag.FieldTags;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProducerDaoImpl implements ProducerDao<Producer> {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

    public ProducerDaoImpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public List<Producer> getAll() throws DBException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Producer> producers = new ArrayList<>();

        try {
            conn = ConnectionHolder.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(Queries.SQL_GET_ALL_PRODUCERS);

            while (rs.next()) {
                Producer producer = new Producer();
                producer.setId(rs.getInt(FieldTags.ID));
                producer.setName(rs.getString(FieldTags.NAME));
                producers.add(producer);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_CATEGORIES, ex);
        } finally {
            dbUtil.close(stmt);
        }

        return producers;
    }
}
