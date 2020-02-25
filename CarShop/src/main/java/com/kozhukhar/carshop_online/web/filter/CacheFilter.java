package com.kozhukhar.carshop_online.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CacheFilter", urlPatterns = "/*")
public class CacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Expires", "-1");
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.setContentType(request.getContentType());
        chain.doFilter(request, resp);
    }


}