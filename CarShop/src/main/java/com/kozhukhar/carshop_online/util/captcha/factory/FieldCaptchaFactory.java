package com.kozhukhar.carshop_online.util.captcha.factory;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.captcha.executor.CaptchaFieldExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class FieldCaptchaFactory extends CaptchaAbstractFactory {

    @Override
    public void startCaptchaExec(HttpServletRequest request, JspWriter out) throws AppException {
        try {
            Runnable captchaRun = new CaptchaFieldExecutor(request, initCaptchaUtil(out, request));
            new Thread(captchaRun).start();
        } catch (IOException ex) {
            throw new AppException(Messages.CAPTCHA_WAS_NOT_LOADED);
        }
    }
}
