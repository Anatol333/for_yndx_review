package com.kozhukhar.carshop_online.db.transact;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.util.DBUtil;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class JdbcTransactionManager implements TransactionManager {

    private DBUtil dbUtil;

    public JdbcTransactionManager() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public <T> T execute(Callable<T> callable) throws AppException {
        Connection connection = null;
        T res = null;
        try {
            connection = ConnectionHolder.getConnection();
            res = callable.call();
            connection.commit();
        } catch (AppException ex) {
            dbUtil.rollback(connection);
            throw new AppException(ex.getMessage());
        } catch (Exception ex) {
            dbUtil.rollback(connection);
        } finally {
            dbUtil.close(connection);
        }
        return res;
    }
}
