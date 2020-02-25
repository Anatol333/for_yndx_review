package com.kozhukhar.carshop_online.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocaleFilterTest {

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private ServletContext context;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private static final String SESSION_TYPE_TEST = "session";
    private static final String COOKIE_TYPE_TEST = "cookie";
    private static final String COOKIE_TIME_TEST = "1200000";
    private static final String RU_LANG = "ru";
    private static final String PAGE = "/page";

    @Before
    public void setUp() throws Exception {
        when(filterConfig.getServletContext()).thenReturn(context);
        when(context.getInitParameter(LOCALE_COOKIE_TIME)).thenReturn(COOKIE_TIME_TEST);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(APP_LOCALE)).thenReturn(RU_LANG);
        when(request.getRequestURI()).thenReturn(PAGE);
    }

    @Test
    public void testFilterShouldReturnRuLangInSession() throws ServletException, IOException {
        when(context.getInitParameter(LOCALE_SAVE_TYPE)).thenReturn(SESSION_TYPE_TEST);

        LocaleFilter localeFilter = new LocaleFilter();
        localeFilter.init(filterConfig);
        localeFilter.doFilter(request, response, chain);

        verify(session).setAttribute(APP_LOCALE, RU_LANG);
    }

    @Test
    public void testFilterShouldReturnRuLangInSessionExists() throws ServletException, IOException {
        when(request.getSession().getAttribute(APP_LOCALE)).thenReturn(RU_LANG);
        when(context.getInitParameter(LOCALE_SAVE_TYPE)).thenReturn(SESSION_TYPE_TEST);

        LocaleFilter localeFilter = new LocaleFilter();
        localeFilter.init(filterConfig);
        localeFilter.doFilter(request, response, chain);

        verify(session).setAttribute(APP_LOCALE, RU_LANG);
    }

    @Test
    public void testFilterShouldReturnRuLangInCookie() throws ServletException, IOException {
        when(context.getInitParameter(LOCALE_SAVE_TYPE)).thenReturn(COOKIE_TYPE_TEST);

        LocaleFilter localeFilter = new LocaleFilter();
        localeFilter.init(filterConfig);
        localeFilter.doFilter(request, response, chain);

        verifyCookie();
    }

    @Test
    public void testFilterShouldReturnRuLangInCookieWhenCookieExists() throws ServletException, IOException {
        when(context.getInitParameter(LOCALE_SAVE_TYPE)).thenReturn(COOKIE_TYPE_TEST);
        whenCookies();

        LocaleFilter localeFilter = new LocaleFilter();
        localeFilter.init(filterConfig);
        localeFilter.doFilter(request, response, chain);

        verifyCookie();
    }

    private void whenCookies() {
        Cookie[] cookies = new Cookie[1];
        Cookie cookie = new Cookie(APP_LOCALE, RU_LANG);
        cookies[0] = cookie;
        when(request.getCookies()).thenReturn(cookies);
    }

    private void verifyCookie() {
        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response).addCookie(cookieCaptor.capture());

        Cookie cookie = cookieCaptor.getValue();
        assertEquals(RU_LANG, cookie.getValue());
    }

}