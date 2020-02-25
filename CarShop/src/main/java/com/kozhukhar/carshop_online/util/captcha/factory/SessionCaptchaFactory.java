package com.kozhukhar.carshop_online.util.captcha.factory;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.captcha.executor.CaptchaSessionExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class SessionCaptchaFactory extends CaptchaAbstractFactory {

    @Override
    public void startCaptchaExec(HttpServletRequest request, JspWriter out) throws AppException {
        try {
            new CaptchaSessionExecutor(request, initCaptchaUtil(out, request));
        } catch (IOException ex) {
            throw new AppException(Messages.CAPTCHA_WAS_NOT_LOADED);
        }
    }
}
