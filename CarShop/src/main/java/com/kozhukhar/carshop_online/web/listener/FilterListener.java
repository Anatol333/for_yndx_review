package com.kozhukhar.carshop_online.web.listener;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.util.SecurityManager;
import com.kozhukhar.carshop_online.web.filter.SecurityFilter;
import org.apache.log4j.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.SECURITY_MANAGER;
import static java.util.EnumSet.allOf;

@WebListener
public class FilterListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(FilterListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SecurityManager securityManager = null;
        try {
            securityManager = new SecurityManager();

            List<String> urls = new ArrayList<>(securityManager.getSecurityMap().keySet());
            String[] array = new String[urls.size()];
            urls.toArray(array);

            ServletContext context = servletContextEvent.getServletContext();
            context.addFilter("Programmatic filter", SecurityFilter.class)
                    .addMappingForUrlPatterns(allOf(DispatcherType.class), false, array);
            context.setAttribute(SECURITY_MANAGER, securityManager);

        } catch (AppException ex) {
            LOG.error(ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Filter listener was destroyed");
    }
}
