package com.kozhukhar.carshop.util;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Stream;

public class LocaleMessageUtil {

    private static final Logger LOG = Logger.getLogger(LocaleMessageUtil.class);

    private String locale;

    private static final String DEFAULT_LOCALE = Localization.EN.getName();

    public LocaleMessageUtil() {
        this.locale = Localization.EN.getName();
    }

    public void setLocale(String newLocalization) {
        List<String> localeElements = Arrays.asList(
                Stream.of(Localization.values()).map(localization -> localization.name().toLowerCase()).toArray(String[]::new)
        );
        if (localeElements.contains(newLocalization)) {
            locale = newLocalization;
        }
    }

    public String localizeMessage(String message) throws AppException {
        String outputMessage = "";
        ResourceBundle rb = ResourceBundle.getBundle("messages",
                Locale.forLanguageTag(locale));
        try {
            if (rb == null) {
                rb = ResourceBundle.getBundle("messages",
                        Locale.forLanguageTag(DEFAULT_LOCALE));
                outputMessage = rb.getString(message);
            } else {
                rb = ResourceBundle.getBundle("messages",
                        Locale.forLanguageTag(locale));
                outputMessage = rb.getString(message);
            }
        } catch (MissingResourceException ex) {
            LOG.debug(Messages.LOCALIZED_MESSAGE_WAS_NOT_FOUND);
            outputMessage = message;
        }
        return outputMessage;
    }
}
