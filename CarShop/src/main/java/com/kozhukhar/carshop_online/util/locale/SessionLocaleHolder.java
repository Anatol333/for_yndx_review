package com.kozhukhar.carshop_online.util.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.APP_LOCALE;

public class SessionLocaleHolder implements LocaleHolder {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        String localeName = (String) request.getSession().getAttribute(APP_LOCALE);
        return localeName == null ? null : Locale.forLanguageTag(localeName);
    }
}
