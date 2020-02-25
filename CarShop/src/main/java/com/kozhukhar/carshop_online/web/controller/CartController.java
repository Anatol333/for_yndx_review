package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import com.kozhukhar.carshop_online.util.purchase.cart.event.CartCommand;
import com.kozhukhar.carshop_online.util.purchase.cart.event.CartEvent;
import com.kozhukhar.carshop_online.web.service.ProductService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_CART;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.CART_PAGE;

@WebServlet(CART_PAGE)
@SuppressWarnings("unchecked")
public class CartController extends HttpServlet {

    private CartEvent cartEvent;

    private ProductService productService;

    private static final Logger LOG = Logger.getLogger(OrderController.class);

    @Override
    public void init(ServletConfig config) {
        cartEvent = (CartEvent) config.getServletContext().getAttribute(CART_EVENT);
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();

        try {
            String actionType = req.getParameter(CART_TYPE);
            String productId = req.getParameter(ID);
            CartManager cartManager = initCartManager(session);

            CartCommand action = cartEvent.getCommand(actionType);
            Integer actionOut = action.execute(productService, Integer.parseInt(productId), cartManager);

            jsonWrite(res, cartManager, actionOut);
            sessionSave(session, cartManager);

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_CART, Messages.CART_UNDEF_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            CartManager cartManager = getCartManager(session);
            session.setAttribute(PRODUCTS_CART, cartManager.findAll());
        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_CART, ex.getMessage());
        }

        req.getRequestDispatcher(JSP_CART).forward(req, res);
        session.removeAttribute(ERROR_CART);
    }

    private void jsonWrite(HttpServletResponse res, CartManager cartManager, Integer actionOut) throws IOException {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put(JSON_OUT, actionOut);
        jsonOut.put(HEADER_VAL, cartManager.numberOfProducts());
        jsonOut.put(CART_PRICE, cartManager.getFullPrice());
        res.getWriter().write(jsonOut.toString());
    }

    private CartManager initCartManager(HttpSession session) {
        CartManager cartManager = (CartManager) session.getAttribute(CART_MNG);
        if (cartManager == null) {
            cartManager = new CartManager();
            session.setAttribute(CART_MNG, cartManager);
        }
        return cartManager;
    }

    private CartManager getCartManager(HttpSession session) throws AppException {
        CartManager cartManager = (CartManager) session.getAttribute(CART_MNG);
        if (cartManager == null || cartManager.findAll().size() == 0) {
            throw new AppException(Messages.CART_IS_EMPTY);
        }
        return cartManager;
    }

    private void sessionSave(HttpSession session, CartManager cartManager) {
        session.setAttribute(COUNT_CART, cartManager.numberOfProducts());
        session.setAttribute(CART_PRICE, cartManager.getFullPrice());
    }

}
