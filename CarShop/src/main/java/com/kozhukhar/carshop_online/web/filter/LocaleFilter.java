package com.kozhukhar.carshop_online.web.filter;

import com.kozhukhar.carshop_online.util.LocaleBundleUtil;
import com.kozhukhar.carshop_online.util.locale.CookieLocaleHolder;
import com.kozhukhar.carshop_online.util.locale.RequestLocaleWrapper;
import com.kozhukhar.carshop_online.util.locale.SessionLocaleHolder;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.ALL_PAGE_PATH;
import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.REG_PAGE;

@WebFilter(filterName = LOCALE_FILTER, urlPatterns = ALL_PAGE_PATH)
public class LocaleFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LocaleFilter.class);
    private String saveType;

    private Integer localeCookieTime;
    private LocaleBundleUtil localeUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeUtil = new LocaleBundleUtil();
        saveType = filterConfig.getServletContext().getInitParameter(LOCALE_SAVE_TYPE);
        localeCookieTime = Integer.valueOf(filterConfig.getServletContext().getInitParameter(LOCALE_COOKIE_TIME));
        LOG.info(LOCALE_FILTER + " was initialized");
    }

    @Override
    public void destroy() {
        LOG.info(LOCALE_FILTER + " was destroyed");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().equals(REG_PAGE)) {
            chain.doFilter(req, resp);
        } else {
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession session = request.getSession();
            Locale requestLocale = request.getLocale();
            Locale savedLocale = getSavedLocale(request);

            String locale = request.getParameter(APP_LOCALE);
            LOG.info("Saved locale is -> " + savedLocale);
            LOG.info("User locale is -> " + locale);
            LOG.info("Request locale is -> " + requestLocale);

            if (locale == null) {
                ResourceBundle resourceBundle = localeUtil.getResourceBundle(request, requestLocale, savedLocale);
                locale = resourceBundle.getLocale().toString();
            }

            LOG.info("Checked locale by filter is -> " + locale);
            saveLocale(response, session, locale);
            chain.doFilter(new RequestLocaleWrapper(request, new Locale(locale)), resp);
        }
    }

    private void saveLocale(HttpServletResponse response, HttpSession session, String locale) {
        if (saveType.equals(SESSION)) {
            session.setAttribute(APP_LOCALE, locale);
        } else {
            Cookie cookie = new Cookie(APP_LOCALE, locale);
            cookie.setMaxAge(localeCookieTime);
            response.addCookie(cookie);
        }
    }

    private Locale getSavedLocale(HttpServletRequest request) {
        Locale savedLocale = null;
        if (saveType.equals(SESSION)) {
            savedLocale = new SessionLocaleHolder().getLocale(request);
        } else {
            savedLocale = new CookieLocaleHolder().getLocale(request);
        }
        return savedLocale;
    }


}
