package com.kozhukhar.carshop_online.web.listener;

import com.kozhukhar.carshop_online.db.DBManager;
import com.kozhukhar.carshop_online.db.dao.ProductDao;
import com.kozhukhar.carshop_online.db.dao.impl.*;
import com.kozhukhar.carshop_online.db.storage.UserStorage;
import com.kozhukhar.carshop_online.db.transact.JdbcTransactionManager;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.UserExistsException;
import com.kozhukhar.carshop_online.util.captcha.CaptchaMap;
import com.kozhukhar.carshop_online.util.purchase.OrderManager;
import com.kozhukhar.carshop_online.util.purchase.cart.event.CartEvent;
import com.kozhukhar.carshop_online.web.service.impl.OrderServiceImpl;
import com.kozhukhar.carshop_online.web.service.impl.ProductServiceImpl;
import com.kozhukhar.carshop_online.web.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.Locale;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(AppContextListener.class);
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Locale.setDefault(new Locale(DEFAULT_LOCALE));
        ServletContext ctx = servletContextEvent.getServletContext();
        String captchaType = servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TYPE);
        String waitingTime = servletContextEvent.getServletContext().getInitParameter(CAPTCHA_WAITING_TIME);
        Integer shopPageLen = Integer.valueOf(servletContextEvent.getServletContext().getInitParameter(SHOP_LENGTH));

        try {
            initContext(ctx, captchaType, waitingTime, shopPageLen);
        } catch (UserExistsException | DBException e) {
            LOG.error(e.getMessage());
        }

        LOG.trace("Context listener initialized.");
    }

    private void initContext(ServletContext ctx, String captchaType, String waitingTime, Integer shopPageLen) throws UserExistsException, DBException {
        ctx.setAttribute(USER_STORAGE, new UserStorage());
        ctx.setAttribute(CAPTCHA_MAP, new CaptchaMap());
        ctx.setAttribute(CAPTCHA_TYPE, captchaType);
        ctx.setAttribute(CAPTCHA_WAITING_TIME, waitingTime);
        ctx.setAttribute(SHOP_LENGTH, shopPageLen);

        ProductDao productDao = new ProductDaoImpl();
        DataSource ds = DBManager.getInstance().getDataSource();
        ctx.setAttribute(DATA_SOURCE, ds);
        ctx.setAttribute(CATEGORY_DAO, new CategoryDaoImpl());
        ctx.setAttribute(PRODUCER_DAO, new ProducerDaoImpl());
        ctx.setAttribute(PRODUCT_DAO, productDao);
        JdbcTransactionManager transactionManager = new JdbcTransactionManager();

        ctx.setAttribute(TRANSACTION_MNG, transactionManager);
        ctx.setAttribute(CART_EVENT, new CartEvent());
        ctx.setAttribute(ORDER_MNG, new OrderManager());
        ctx.setAttribute(ORDER_SERVICE, new OrderServiceImpl(transactionManager, new OrderDaoImpl()));
        ctx.setAttribute(PRODUCT_SERVICE, new ProductServiceImpl(transactionManager, productDao));
        ctx.setAttribute(USER_SERVICE, new UserServiceImpl(transactionManager, new UserDaoImpl(), new RoleDaoImpl()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.trace("Context listener destroyed.");
    }
}
