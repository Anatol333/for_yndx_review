package com.kozhukhar.carshop_online.util.captcha.factory;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.util.captcha.CaptchaUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.CAPTCHA_HIDDEN;

public abstract class CaptchaAbstractFactory {

    private static final String HTML_HIDDEN_TAG = " <input type=\"hidden\" name=\"id_captcha\" value=\" ";
    private static final String HTML_IMAGE_CAPTURE = "<br><img alt=\"img\" src=\"data:image/jpeg;base64,";
    private static final String HTML_CLOSE_TAG = "\">";

    public abstract void startCaptchaExec(HttpServletRequest request, JspWriter out) throws AppException;

    CaptchaUtil initCaptchaUtil(JspWriter out, HttpServletRequest request) throws IOException {
        CaptchaUtil captchaUtil = new CaptchaUtil();
        out.write(getCaptureImage(captchaUtil.getByteImage(), request));
        return captchaUtil;
    }

    private String getCaptureImage(byte[] byteImage, HttpServletRequest request) {
        String outResult = "";

        if (request.getSession(false) != null) {
            String captchaCodeHidden = (String) request.getSession().getAttribute(CAPTCHA_HIDDEN);
            outResult += HTML_HIDDEN_TAG + captchaCodeHidden + HTML_CLOSE_TAG;
        }

        byte[] encodeBase64 = Base64.getEncoder().encode(byteImage);
        outResult += HTML_IMAGE_CAPTURE + new String(encodeBase64, StandardCharsets.UTF_8) + HTML_CLOSE_TAG;
        return outResult;
    }
}
