package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.db.dao.impl.CategoryDaoImpl;
import com.kozhukhar.carshop_online.db.dao.impl.ProducerDaoImpl;
import com.kozhukhar.carshop_online.db.dao.impl.ProductDaoImpl;
import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.db.storage.ProductStorage;
import com.kozhukhar.carshop_online.db.transact.TransactionManager;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.web.resource_tag.JspPaths;
import com.kozhukhar.carshop_online.web.resource_tag.PagePaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

@WebServlet(PagePaths.SHOP_PAGE)
public class ShopController extends HttpServlet {

    private ProductDaoImpl productDaoImpl;
    private CategoryDaoImpl categoryDaoImpl;
    private ProducerDaoImpl producerDaoImpl;
    private TransactionManager transactionManager;

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        showPage(req);
        req.getRequestDispatcher(JspPaths.JSP_SHOP).forward(req, res);
    }

    @Override
    public void init(ServletConfig config) {
        productDaoImpl = (ProductDaoImpl) config.getServletContext().getAttribute(PRODUCT_DAO);
        categoryDaoImpl = (CategoryDaoImpl) config.getServletContext().getAttribute(CATEGORY_DAO);
        producerDaoImpl = (ProducerDaoImpl) config.getServletContext().getAttribute(PRODUCER_DAO);
        transactionManager = (TransactionManager) config.getServletContext().getAttribute(TRANSACTION_MNG);
    }

    private void showPage(HttpServletRequest req) {
        String pageParam = req.getParameter(PAGE_NUM);

        try {
            Integer pageLen = initPageLen(req);

            ProductStorage productStorage = initProductStore(req, transactionManager);
            List<Product> products = productStorage.getAllProducts(productDaoImpl);
            int numberOfPages = getNumberOfPages(pageLen, products);
            Integer page = initPage(pageParam, numberOfPages);
            List<Product> productsPage = productStorage.getProductsByPage(page - 1, pageLen, productDaoImpl);

            setAttributes(req, numberOfPages, page, productsPage);

        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            req.getSession().setAttribute(ERROR_MSG, Messages.CATALOG_SERVER_ERROR);
        } finally {
            req.getSession().removeAttribute(ERROR_MSG);
            req.getSession().removeAttribute(SUCCESS_MSG);
        }
    }

    private Integer initPageLen(HttpServletRequest req) {
        String pageLenParam = req.getParameter(PAGE_LEN);
        Integer pageLen = (Integer) req.getServletContext().getAttribute(SHOP_LENGTH);
        if (pageLenParam != null && !pageLenParam.equals(EMPTY_STRING)) {
            pageLen = Integer.parseInt(pageLenParam);
            req.setAttribute(PAGE_LEN, pageLen);
        }
        return pageLen;
    }

    private int getNumberOfPages(Integer pageLen, List<Product> products) {
        int numberOfPages = products.size() / pageLen;
        if (products.size() % pageLen != 0) {
            numberOfPages++;
        }
        return numberOfPages;
    }

    private ProductStorage initProductStore(HttpServletRequest req, TransactionManager transactionManager) {
        String nameOfProduct = req.getParameter(NAME_PRODUCT_FILTER);
        String priceFrom = req.getParameter(PRICE_FROM_FILTER);
        String priceTo = req.getParameter(PRICE_TO_FILTER);
        String[] categories = req.getParameterValues(CATEGORIES_FILTER);
        String[] producers = req.getParameterValues(PRODUCERS_FILTER);
        String sort = req.getParameter(SORT_TYPE);

        setFilterParams(req, nameOfProduct, priceFrom, priceTo, categories, producers, sort);

        return new ProductStorage.QueryBuilder()
                .byCategories(categories)
                .byName(nameOfProduct)
                .byPrice(priceFrom, priceTo)
                .byProducers(producers)
                .sort(sort)
                .build(transactionManager);
    }

    private Integer initPage(String pageParam, Integer pages) {
        Integer page = DEF_SHOP_PAGE;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        if (page < 1 || page > pages) {
            page = DEF_SHOP_PAGE;
        }
        return page;
    }

    private void setFilterParams(HttpServletRequest req, String nameOfProduct, String priceFrom, String priceTo, String[] categories, String[] producers, String sort) {
        req.setAttribute(NAME_PRODUCT_FILTER, nameOfProduct);
        req.setAttribute(PRICE_FROM_FILTER, priceFrom);
        req.setAttribute(PRICE_TO_FILTER, priceTo);
        req.setAttribute(CATEGORIES_FILTER, categories);
        req.setAttribute(PRODUCERS_FILTER, producers);
        req.setAttribute(SORT_TYPE, sort);
    }

    private void setAttributes(HttpServletRequest req, int numberOfPages, Integer page, List<Product> productsPage) throws DBException {
        req.setAttribute(CURRENT_PAGE, page);
        req.setAttribute(NUM_PAGES, numberOfPages);
        req.setAttribute(PRODUCT_LIST, productsPage);
        req.setAttribute(NEXT_PAGE, page != numberOfPages);
        req.setAttribute(PREV_PAGE, page > 1);
        req.setAttribute(CATEGORIES, categoryDaoImpl.getAll());
        req.setAttribute(PRODUCERS, producerDaoImpl.getAll());
    }


}
