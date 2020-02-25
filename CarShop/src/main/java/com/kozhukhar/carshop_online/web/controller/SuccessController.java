package com.kozhukhar.carshop_online.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_SUCCESS;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.SUCCESS_PAGE;

@WebServlet(SUCCESS_PAGE)
public class SuccessController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(JSP_SUCCESS).forward(req, resp);
    }
}
