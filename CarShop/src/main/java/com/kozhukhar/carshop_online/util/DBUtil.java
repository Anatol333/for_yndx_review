package com.kozhukhar.carshop_online.util;

import com.kozhukhar.carshop_online.exception.Messages;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final Logger LOG = Logger.getLogger(DBUtil.class);

    public void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                LOG.error(Messages.CANNOT_ROLLBACK_TRANSACTION, ex);
            }
        }
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.CONNECTION_CANNOT_CLOSE, ex);
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.STATEMENT_CANNOT_CLOSE, ex);
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.RS_CANNOT_CLOSE, ex);
            }
        }
    }

    public void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

}
