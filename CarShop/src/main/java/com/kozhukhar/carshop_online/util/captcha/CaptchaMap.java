package com.kozhukhar.carshop_online.util.captcha;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CaptchaMap {

    private Map<String, String> captchaElements;

    public CaptchaMap() {
        captchaElements = new HashMap<>();
    }

    public String addCaptcha(String captcha) {
        String uniqueKey = UUID.randomUUID().toString();
        captchaElements.put(uniqueKey, captcha);
        return uniqueKey;
    }

    public void removeCaptcha(String captcha) {
        captchaElements.values().remove(captcha);
    }

    public boolean exists(String captchaCode) {
        return captchaElements.containsValue(captchaCode);
    }

    public String getCaptchaByKey(String key) {
        return captchaElements.get(key);
    }
}
