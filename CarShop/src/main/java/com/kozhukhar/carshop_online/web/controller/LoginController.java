package com.kozhukhar.carshop_online.web.controller;


import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.web.resource_tag.PagePaths;
import com.kozhukhar.carshop_online.web.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_LOGIN;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.CART_PAGE;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.LOGIN_PAGE;

@WebServlet(PagePaths.LOGIN_PAGE)
public class LoginController extends HttpServlet {

    private UserService userServiceImpl;

    private static final Logger LOG = Logger.getLogger(OrderController.class);

    private static final Integer MAXIMUM_LOGIN_ATTEMPTS = 3;

    private static final Integer BAN_TIME_MLS = 300_000;

    @Override
    public void init(ServletConfig config) {
        userServiceImpl = (UserService) config.getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        User user = new User();
        HttpSession session = req.getSession();
        user.setUsername(req.getParameter(USERNAME));
        user.setPassword(req.getParameter(PASSWORD));
        String fromPage = req.getParameter(FROM_TAG);
        Integer attemptsValue = session.getAttribute(LOGIN_ATTEMPT) != null
                ? (Integer) session.getAttribute(LOGIN_ATTEMPT) : ZERO_VALUE;

        try {
            if (attemptsValue.equals(MAXIMUM_LOGIN_ATTEMPTS)) {
                userServiceImpl.banUser(user.getUsername(), BAN_TIME_MLS);
            }
            session.setAttribute(LOGGED_USER, userServiceImpl.loginUser(user));
            session.setAttribute(SUCCESS_MSG, Messages.USER_LOGGED_SUCCESSFULLY);
            session.setAttribute(LOGIN_ATTEMPT, ZERO_VALUE);
        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            fromPage = LOGIN_PAGE;
            session.setAttribute(LOGIN_ATTEMPT, ++attemptsValue);
            session.setAttribute(ERROR_MSG, ex.getMessage());
        }

        redirect(req, res, fromPage);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.setAttribute(FROM_TAG, req.getParameter(FROM_TAG));
        req.getRequestDispatcher(JSP_LOGIN).forward(req, res);

        HttpSession session = req.getSession();
        session.removeAttribute(ERROR_MSG);
        session.removeAttribute(SUCCESS_MSG);
    }

    private void redirect(HttpServletRequest req, HttpServletResponse res, String fromPage) throws IOException {
        String redirectPage = Optional
                .ofNullable(fromPage)
                .orElse(CART_PAGE);
        res.sendRedirect(req.getContextPath() + redirectPage);
    }
}