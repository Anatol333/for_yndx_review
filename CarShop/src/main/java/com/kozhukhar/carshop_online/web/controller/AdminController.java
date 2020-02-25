package com.kozhukhar.carshop_online.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_ADMIN;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.ADMIN_PAGE;

@WebServlet(ADMIN_PAGE)
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(JSP_ADMIN).forward(req, resp);
    }
}
