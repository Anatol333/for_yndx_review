package com.kozhukhar.carshop_online.util.validate;

import com.kozhukhar.carshop_online.db.bean.RegUserBean;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.captcha.CaptchaUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String USER_VALIDATE_PATTERN = "^[a-zA-Z0-9_-]{5,15}$";
    private static final String USER_EMAIL_VALIDATE = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final Integer EIGHT_VALIDATE = 8;
    private static final Integer MIN_VALIDATE = 5;
    private static final Integer MAX_VALIDATE = 15;
    private static final String NOTHING_STR = "";
    private static final String SPACE_STR = " ";

    private Pattern pattern;
    private CaptchaUtil captchaUtil;
    private RegUserBean userBean;
    private HttpServletRequest req;

    public UserValidator(RegUserBean userBean, HttpServletRequest req) {
        this.userBean = userBean;
        this.req = req;
        pattern = Pattern.compile(USER_VALIDATE_PATTERN);
        captchaUtil = new CaptchaUtil();
    }

    public List<String> validateRegUser() {
        List<String> errorList = new ArrayList<>();
        validateUsername(errorList);
        validateName(errorList);
        validateSurname(errorList);
        validatePassword(errorList);
        validateCaptcha(errorList);
        validateNews();

        return errorList;
    }

    private void validateUsername(List<String> errorList) {
        String error = NOTHING_STR;
        pattern = Pattern.compile(USER_EMAIL_VALIDATE);

        if (userBean.getUsername() == null
                || userBean.getUsername().length() < MIN_VALIDATE
                || userBean.getUsername().length() > MAX_VALIDATE) {
            error = Messages.USER_NAME_WRONG_DIAPASON;
        } else if (!pattern.matcher(userBean.getEmail()).matches()) {
            error = Messages.USERS_EMAIL_HAS_WRONG_FORMAT;
        }
        addError(errorList, error);
    }

    private void validateName(List<String> errorList) {
        String error = NOTHING_STR;
        if (userBean.getName().replaceAll(SPACE_STR, NOTHING_STR).equals(NOTHING_STR)) {
            error = Messages.NAME_IS_NULL;
        }
        addError(errorList, error);
    }

    private void validateSurname(List<String> errorList) {
        String error = NOTHING_STR;
        if (userBean.getSurname().replaceAll(SPACE_STR, NOTHING_STR).equals(NOTHING_STR)) {
            error = Messages.SURNAME_IS_NULL;
        }
        addError(errorList, error);
    }

    private void validatePassword(List<String> errorList) {
        String error = NOTHING_STR;
        if (userBean.getPassword() == null || userBean.getPassword().length() < EIGHT_VALIDATE) {
            error = Messages.PASSWORD_IS_LESS;
        } else if (userBean.getPasswordRep() == null || !userBean.getPassword().equals(userBean.getPasswordRep())) {
            error = Messages.PASSWORDS_ARE_NOT_EQUALS;
        }
        addError(errorList, error);
    }

    private void validateCaptcha(List<String> errorList) {
        String error = NOTHING_STR;
        if (!captchaUtil.validateUserCaptcha(req, userBean.getCaptchaCode())) {
            error = Messages.CAPTCHA_ERROR;
        }
        addError(errorList, error);
    }

    private void validateNews() {
        if (null == userBean.getNews()) {
            userBean.setNews(false);
        }
    }

    private void addError(List<String> errorList, String error) {
        if (!error.equals(NOTHING_STR)) {
            errorList.add(error);
        }
    }
}
