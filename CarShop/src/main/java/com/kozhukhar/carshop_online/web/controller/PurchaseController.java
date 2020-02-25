package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.purchase.OrderManager;
import com.kozhukhar.carshop_online.util.purchase.OrderStatus;
import com.kozhukhar.carshop_online.util.purchase.PayTypes;
import com.kozhukhar.carshop_online.web.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_PURCHASE;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.PURCHASE_PAGE;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.SUCCESS_PAGE;

@WebServlet(PURCHASE_PAGE)
public class PurchaseController extends HttpServlet {

    private OrderService orderService;

    private static final Logger LOG = Logger.getLogger(PurchaseController.class);

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession();
        OrderManager orderManager = (OrderManager) session.getAttribute(ORDER_MNG);

        try {
            isOrderExists(orderManager);
            String status = orderManager.info().getStatus();
            String type = isTypeExists(req);

            order(orderManager, status, type);
            sessionRemove(session);

        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            orderManager.cancel(ex.getMessage());
            session.setAttribute(ERROR_MSG, ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_MSG, Messages.ORDER_UNDEF_ERROR);
        }

        resp.sendRedirect(req.getContextPath() + SUCCESS_PAGE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(PAY_TYPE, req.getParameter(PAY_TYPE));

        req.getRequestDispatcher(JSP_PURCHASE).forward(req, resp);
        session.removeAttribute(ERROR_MSG);
        session.removeAttribute(SUCCESS_MSG);
    }

    private void order(OrderManager orderManager, String status, String type) throws AppException {

        orderManager.info().setPayType(type);
        if (type.equals(PayTypes.POST_OFFICE.getName())) {
            orderManager.info().setStatus(OrderStatus.SENT.getName());
        } else if (status.equals(OrderStatus.SENT.getName())) {
            orderManager.info().setStatus(OrderStatus.ACCEPTED.getName());
        }
        orderManager.order(orderService);
    }

    private void sessionRemove(HttpSession session) {
        session.removeAttribute(CART_MNG);
        session.removeAttribute(ORDER_MNG);
        session.removeAttribute(PRODUCTS_CART);
        session.removeAttribute(COUNT_CART);
        session.removeAttribute(ORDER_PRODUCTS);
    }

    private String isTypeExists(HttpServletRequest req) throws AppException {
        String type = (String) req.getSession().getAttribute(PAY_TYPE);
        if (type == null) {
            throw new AppException(Messages.SELECT_PAY_TYPE);
        }
        return type;
    }

    private void isOrderExists(OrderManager orderManager) throws AppException {
        if (orderManager == null) {
            throw new AppException(Messages.CART_IS_EMPTY);
        }
    }
}
