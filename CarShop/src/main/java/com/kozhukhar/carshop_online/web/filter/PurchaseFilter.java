package com.kozhukhar.carshop_online.web.filter;

import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.purchase.OrderManager;
import com.kozhukhar.carshop_online.util.purchase.OrderStatus;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.ERROR_MSG;
import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.ORDER_MNG;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.ORDER_PAGE;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.PURCHASE_PAGE;

@WebFilter(filterName = "PurchaseFilter", urlPatterns = PURCHASE_PAGE)
public class PurchaseFilter implements Filter {

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(PurchaseFilter.class);

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("Purchase filter was started.");
    }

    public void destroy() {
        LOG.debug("Purchase filter was destroyed.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        OrderManager orderManager = (OrderManager) session.getAttribute(ORDER_MNG);
        if (orderManager != null && OrderStatus.CONFIRMED.getName().equals(orderManager.info().getStatus())) {
            chain.doFilter(req, resp);
        } else {
            session.setAttribute(ERROR_MSG, Messages.ORDER_MUST_BE_CONFIRMED);
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect(request.getContextPath() + ORDER_PAGE);
        }
    }
}
