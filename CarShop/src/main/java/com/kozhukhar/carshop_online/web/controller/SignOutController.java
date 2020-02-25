package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.web.resource_tag.PagePaths;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.LOGGED_USER;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.SINGLE_PAGE;

@WebServlet(PagePaths.SIGN_OUT_PAGE)
public class SignOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(LOGGED_USER);
        res.sendRedirect(req.getContextPath() + SINGLE_PAGE);
    }
}
