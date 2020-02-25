package com.kozhukhar.carshop_online.util.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface LocaleHolder {

    Locale getLocale(HttpServletRequest request);

}
