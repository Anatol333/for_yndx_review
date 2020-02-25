package com.kozhukhar.carshop_online.util.captcha;

import com.kozhukhar.carshop_online.util.ContextDataUtil;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.Captcha;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

public class CaptchaUtil extends AppController {

    private String captchaCode;

    private static final int MAX_NUMBER = 1000000;

    public CaptchaUtil() {
        Random random = new Random(System.currentTimeMillis());
        int num = random.nextInt(MAX_NUMBER);
        captchaCode = String.format("%05d", num);
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public byte[] getByteImage() {
        return Captcha.generateImage(captchaCode);
    }

    public boolean validateUserCaptcha(HttpServletRequest request, String captchaCode) {
        ContextDataUtil contextDataUtil = new ContextDataUtil(request.getServletContext());
        String captchaType = contextDataUtil.getCaptchaTypeFromContext();

        if (SESSION.equals(captchaType)) {
            String sessionCaptcha = (String) request.getSession().getAttribute(CAPTCHA_KEY);
            long sessionDate = (long) request.getSession().getAttribute(DATE_START_KEY);
            Integer timeMls = contextDataUtil.getCaptchaWaitingTime();
            return captchaCode != null
                    && System.currentTimeMillis() - timeMls < sessionDate
                    && captchaCode.equals(sessionCaptcha);

        } else if (COOKIE.equals(captchaType) && contextDataUtil.getCaptchaMap().exists(captchaCode)) {
            return true;
        } else if (FIELD.equals(captchaType)) {
            String captchaHidden = String.valueOf(request.getSession().getAttribute(CAPTCHA_HIDDEN));
            CaptchaMap captchaMap = contextDataUtil.getCaptchaMap();
            String captcha = captchaMap.getCaptchaByKey(captchaHidden);

            return captchaCode != null && captchaCode.equals(captcha);
        }
        return false;
    }
}
