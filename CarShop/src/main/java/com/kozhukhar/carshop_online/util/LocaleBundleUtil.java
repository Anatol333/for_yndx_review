package com.kozhukhar.carshop_online.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.LOCALE_BUNDLE;
import static java.util.ResourceBundle.Control.FORMAT_PROPERTIES;

public class LocaleBundleUtil {

    public ResourceBundle getResourceBundle(HttpServletRequest request, Locale requestLocale, Locale savedLocale) {
        ResourceBundle resourceBundle;
        if (savedLocale == null) {
            resourceBundle = getRequestBundle(request, requestLocale);
        } else {
            resourceBundle = initLocaleBundle(savedLocale);
        }

        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(LOCALE_BUNDLE, Locale.getDefault());
        }
        return resourceBundle;
    }

    private ResourceBundle initLocaleBundle(Locale locale) {
        return ResourceBundle.getBundle(LOCALE_BUNDLE, locale,
                ResourceBundle.Control.getControl(FORMAT_PROPERTIES));
    }

    private ResourceBundle getRequestBundle(HttpServletRequest request, Locale requestLocale) {
        ResourceBundle resourceBundle;
        if (requestLocale == null) {
            resourceBundle = getDesiredBundle(request);
        } else {
            resourceBundle = initLocaleBundle(requestLocale);
        }
        return resourceBundle;
    }

    private ResourceBundle getDesiredBundle(HttpServletRequest request) {
        ResourceBundle resourceBundle = null;
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements() && resourceBundle == null) {
            Locale desiredLocale = locales.nextElement();
            resourceBundle = initLocaleBundle(desiredLocale);
        }
        return resourceBundle;
    }
}
