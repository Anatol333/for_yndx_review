package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.purchase.OrderManager;
import com.kozhukhar.carshop_online.util.purchase.cart.CartManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_ORDER;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.ORDER_PAGE;

@WebServlet(ORDER_PAGE)
public class OrderController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OrderController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            CartManager cartManager = (CartManager) session.getAttribute(CART_MNG);
            OrderManager orderManager = new OrderManager();
            User loggedUser = (User) session.getAttribute(LOGGED_USER);

            isLoggedUser(loggedUser);
            orderManager.orderInit(cartManager, loggedUser.getId());
            orderManager.confirm();

            session.setAttribute(ORDER_PRODUCTS, orderManager.info().getProducts());
            session.setAttribute(ORDER_PRICE, orderManager.info().getPrice());
            session.setAttribute(ORDER_MNG, orderManager);

        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_ORDER, ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_ORDER, Messages.CART_UNDEF_ERROR);
        }

        res.sendRedirect(ORDER_PAGE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            User loggedUser = (User) session.getAttribute(LOGGED_USER);
            isLoggedUser(loggedUser);
        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_ORDER, ex.getMessage());
        } finally {
            req.getRequestDispatcher(JSP_ORDER).forward(req, res);
            session.removeAttribute(ERROR_ORDER);
            session.removeAttribute(ERROR_MSG);
            session.removeAttribute(SUCCESS_MSG);
        }
    }

    private void isLoggedUser(User loggedUser) throws AppException {
        if (loggedUser == null) {
            throw new AppException(Messages.USER_WAS_NOT_LOGGED);
        }
    }

}
