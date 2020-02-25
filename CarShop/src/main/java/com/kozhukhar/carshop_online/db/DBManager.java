package com.kozhukhar.carshop_online.db;

import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DB Manager. Works with MYSQL DB.
 * <p>
 * This class using singleton pattern.
 * Method {@link #getInstance} is synchronized.
 * </p>
 *
 * @author Anatol Kozhukhar
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    private DataSource ds;


    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Constructor of singleton class DBUtil.
     *
     * @throws DBException
     */
    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup("jdbc/ResConDB");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public DataSource getDataSource() {
        return ds;
    }
}
