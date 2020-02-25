package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.web.resource_tag.PagePaths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.ERROR_MSG;
import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.SUCCESS_MSG;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_INDEX;

@WebServlet(PagePaths.INDEX_PAGE)
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher(JSP_INDEX).forward(req, res);
        req.getSession().removeAttribute(ERROR_MSG);
        req.getSession().removeAttribute(SUCCESS_MSG);
    }
}
