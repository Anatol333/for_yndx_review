package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.RoleDao;
import com.kozhukhar.carshop_online.db.transact.ConnectionHolder;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.util.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

    public RoleDaoImpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public void addRoleToUser(Integer userId, String role) throws DBException {
        PreparedStatement pstmt = null;
        try {
            Connection conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_ADD_ROLE_TO_USER);
            int atr = 1;
            pstmt.setInt(atr++, userId);
            pstmt.setString(atr, role);

            pstmt.executeUpdate();
        } catch (SQLException | DBException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(ex.getMessage(), ex);
        } finally {
            dbUtil.close(pstmt);
        }
    }


}
