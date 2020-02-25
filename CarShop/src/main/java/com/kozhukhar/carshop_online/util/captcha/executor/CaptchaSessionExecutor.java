package com.kozhukhar.carshop_online.util.captcha.executor;

import com.kozhukhar.carshop_online.util.captcha.CaptchaUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.CAPTCHA_KEY;
import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.DATE_START_KEY;

public class CaptchaSessionExecutor {

    private HttpServletRequest request;

    private CaptchaUtil captchaUtil;

    public CaptchaSessionExecutor(HttpServletRequest request, CaptchaUtil captchaUtil) {
        this.request = request;
        this.captchaUtil = captchaUtil;
        run();
    }

    public void run() {
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_KEY, captchaUtil.getCaptchaCode());
        session.setAttribute(DATE_START_KEY, System.currentTimeMillis());
    }
}
