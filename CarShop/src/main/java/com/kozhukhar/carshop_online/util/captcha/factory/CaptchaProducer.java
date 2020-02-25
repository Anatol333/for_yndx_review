package com.kozhukhar.carshop_online.util.captcha.factory;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

public class CaptchaProducer {

    public CaptchaAbstractFactory createFactory(String captchaType) {
        CaptchaAbstractFactory captchaAbstractFactory = null;
        if (SESSION.equals(captchaType)) {
            captchaAbstractFactory = new SessionCaptchaFactory();
        } else if (COOKIE.equals(captchaType)) {
            captchaAbstractFactory = new CookieCaptchaFactory();
        } else if (FIELD.equals(captchaType)) {
            captchaAbstractFactory = new FieldCaptchaFactory();
        }
        return captchaAbstractFactory;
    }
}
