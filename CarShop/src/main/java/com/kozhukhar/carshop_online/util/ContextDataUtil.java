package com.kozhukhar.carshop_online.util;

import com.kozhukhar.carshop_online.db.storage.UserStorage;
import com.kozhukhar.carshop_online.util.captcha.CaptchaMap;

import javax.servlet.ServletContext;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

public class ContextDataUtil {

    private ServletContext ctx;

    public ContextDataUtil(ServletContext ctx) {
        this.ctx = ctx;
    }

    public UserStorage getStorageFromContext() {
        return (UserStorage) ctx.getAttribute(USER_STORAGE);
    }

    public String getCaptchaTypeFromContext() {
        return (String) ctx.getAttribute(CAPTCHA_TYPE);
    }

    public Integer getCaptchaWaitingTime() {
        return Integer.parseInt((String) ctx.getAttribute(CAPTCHA_WAITING_TIME));
    }

    public CaptchaMap getCaptchaMap() {
        return (CaptchaMap) ctx.getAttribute(CAPTCHA_MAP);
    }
}
