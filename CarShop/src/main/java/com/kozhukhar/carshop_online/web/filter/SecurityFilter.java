package com.kozhukhar.carshop_online.web.filter;

import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.SecurityManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.ACCESS_BLOCKED_PAGE;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.LOGIN_PAGE;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(SecurityFilter.class);

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("SecurityFilter filter was started.");
    }

    public void destroy() {
        LOG.debug("SecurityFilter filter was destroyed.");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        SecurityManager securityManager = (SecurityManager) context.getAttribute(SECURITY_MANAGER);

        User user = (User) session.getAttribute(LOGGED_USER);
        if (user != null) {
            List<String> roles = securityManager.getRolesByUrl(request.getRequestURI());
            boolean userAccess = getUserAccess(user, roles);
            if (userAccess) {
                chain.doFilter(req, resp);
            } else {
                LOG.debug("Account has not access to the current page!");
                response.sendRedirect(request.getContextPath() + ACCESS_BLOCKED_PAGE);
            }
        } else {
            LOG.debug("Account not in session.");
            session.setAttribute(ERROR_MSG, Messages.USER_WAS_NOT_LOGGED);
            String url = request.getRequestURI();
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE + FROM_ATTRIBUTE + url);
        }
    }

    private boolean getUserAccess(User user, List<String> roles) {
        return roles
                .stream()
                .anyMatch((elem) -> user.getRoles().contains(elem));
    }

}
