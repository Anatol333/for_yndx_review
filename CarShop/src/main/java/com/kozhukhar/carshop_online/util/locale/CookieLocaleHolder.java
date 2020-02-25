package com.kozhukhar.carshop_online.util.locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.APP_LOCALE;

public class CookieLocaleHolder implements LocaleHolder {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Locale cookieLocale = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (APP_LOCALE.equals(cookie.getName())) {
                    cookieLocale = Locale.forLanguageTag(cookie.getValue());
                }
            }
        }
        return cookieLocale;
    }
}
