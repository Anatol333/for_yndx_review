package com.kozhukhar.carshop_online.db.transact;

import com.kozhukhar.carshop_online.db.DBManager;
import com.kozhukhar.carshop_online.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolder {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException, DBException {
        if (connectionThreadLocal.get() == null || connectionThreadLocal.get().isClosed()) {
            Connection connection = DBManager.getInstance().getDataSource().getConnection();
            connectionThreadLocal.set(connection);
        }
        return connectionThreadLocal.get();
    }
}
