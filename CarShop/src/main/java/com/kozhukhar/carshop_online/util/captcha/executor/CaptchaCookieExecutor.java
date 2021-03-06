package com.kozhukhar.carshop_online.util.captcha.executor;

import com.kozhukhar.carshop_online.util.ContextDataUtil;
import com.kozhukhar.carshop_online.util.captcha.CaptchaMap;
import com.kozhukhar.carshop_online.util.captcha.CaptchaUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CaptchaCookieExecutor implements Runnable {

    private HttpServletRequest request;

    private CaptchaUtil captchaUtil;

    private static final Logger LOG = Logger.getLogger(CaptchaCookieExecutor.class);

    public CaptchaCookieExecutor(HttpServletRequest request, CaptchaUtil captchaUtil) {
        this.request = request;
        this.captchaUtil = captchaUtil;
    }

    @Override
    public void run() {
        ContextDataUtil contextData = new ContextDataUtil(request.getServletContext());
        CaptchaMap captchaMap = contextData.getCaptchaMap();
        Integer timeMls = contextData.getCaptchaWaitingTime();

        try {
            captchaMap.addCaptcha(captchaUtil.getCaptchaCode());
            Thread.sleep(timeMls);

        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            captchaMap.removeCaptcha(captchaUtil.getCaptchaCode());
        }
    }
}
