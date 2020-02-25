package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.CategoriesDao;
import com.kozhukhar.carshop_online.db.dto.Category;
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

public class CategoryDaoImpl implements CategoriesDao<Category> {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

    public CategoryDaoImpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public List<Category> getAll() throws DBException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Category> categories = new ArrayList<>();

        try {
            conn = ConnectionHolder.getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(Queries.SQL_GET_ALL_CATEGORIES);

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(FieldTags.ID));
                category.setName(rs.getString(FieldTags.NAME));
                categories.add(category);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_CATEGORIES, ex);
        } finally {
            dbUtil.close(stmt);
        }

        return categories;
    }
}
