package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.db.storage.UserStorage;
import com.kozhukhar.carshop_online.db.bean.RegUserBean;
import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.util.captcha.CaptchaMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class RegControllerTest extends Mockito {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext context;

    @Mock
    private CaptchaMap captchaMapHidden;

    private RegUserBean userReg = new RegUserBean();

    private static final String USER_FIELD = "testUser";
    private static final String EMAIL_USER = "testUser22@gmail.com";
    private static final User TEST_USER = new User(USER_FIELD, USER_FIELD, EMAIL_USER, true);
    private static final String REGISTRATION_TIME = "100000";
    private static final String USER_FIELD_WRONG = "other_field";

    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(USER_STORAGE)).thenReturn(new UserStorage());

        initUserReg();
    }

    @After
    public void reset_mocks() {
        reset(request, response, session, context, captchaMapHidden);
    }


    @Test
    public void testSessionWrongCaptcha() throws IOException {
        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD_WRONG);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(SESSION);
        when(session.getAttribute(DATE_START_KEY)).thenReturn(System.currentTimeMillis());
        when(context.getAttribute(CAPTCHA_WAITING_TIME)).thenReturn(REGISTRATION_TIME);
        initRequestUserReg();

        new RegController().doPost(request, response);
        assertFalse(((UserStorage) context.getAttribute(USER_STORAGE)).mapExists(TEST_USER));
    }

    @Test
    public void testSessionRegistration() throws IOException {
        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(SESSION);
        when(session.getAttribute(DATE_START_KEY)).thenReturn(System.currentTimeMillis());
        when(context.getAttribute(CAPTCHA_WAITING_TIME)).thenReturn(REGISTRATION_TIME);
        initRequestUserReg();

        new RegController().doPost(request, response);
    }

    @Test
    public void testCookieWrongCaptcha() throws IOException {
        CaptchaMap captchaMap = new CaptchaMap();
        captchaMap.addCaptcha(USER_FIELD_WRONG);

        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(COOKIE);
        when(context.getAttribute(CAPTCHA_MAP)).thenReturn(captchaMap);
        initRequestUserReg();

        new RegController().doPost(request, response);
        assertFalse(((UserStorage) context.getAttribute(USER_STORAGE)).mapExists(TEST_USER));
    }

    @Test
    public void testCookieRegistration() throws IOException {
        CaptchaMap captchaMap = new CaptchaMap();
        captchaMap.addCaptcha(USER_FIELD);

        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(COOKIE);
        when(context.getAttribute(CAPTCHA_MAP)).thenReturn(captchaMap);
        initRequestUserReg();

        new RegController().doPost(request, response);
    }

    @Test
    public void testFieldWrongCaptcha() throws IOException {
        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(FIELD);
        when(context.getAttribute(CAPTCHA_MAP)).thenReturn(captchaMapHidden);
        when(session.getAttribute(CAPTCHA_HIDDEN)).thenReturn(USER_FIELD);
        when(captchaMapHidden.getCaptchaByKey(USER_FIELD)).thenReturn(USER_FIELD_WRONG);
        initRequestUserReg();

        new RegController().doPost(request, response);
        assertFalse(((UserStorage) context.getAttribute(USER_STORAGE)).mapExists(TEST_USER));
    }

    @Test
    public void testFieldRegistration() throws IOException {
        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(FIELD);
        when(context.getAttribute(CAPTCHA_MAP)).thenReturn(captchaMapHidden);
        when(session.getAttribute(CAPTCHA_HIDDEN)).thenReturn(USER_FIELD);
        when(captchaMapHidden.getCaptchaByKey(USER_FIELD)).thenReturn(USER_FIELD);
        initRequestUserReg();

        new RegController().doPost(request, response);
    }

    @Test
    public void testUserExistRegistration() throws IOException {
        when(session.getAttribute(CAPTCHA_KEY)).thenReturn(USER_FIELD);
        when(context.getAttribute(CAPTCHA_TYPE)).thenReturn(FIELD);
        when(context.getAttribute(CAPTCHA_MAP)).thenReturn(captchaMapHidden);
        when(session.getAttribute(CAPTCHA_HIDDEN)).thenReturn(USER_FIELD);
        when(captchaMapHidden.getCaptchaByKey(USER_FIELD)).thenReturn(USER_FIELD);
        initRequestUserReg();

        new RegController().doPost(request, response);
        Integer sizeMapBefore = getMap();
        new RegController().doPost(request, response);
        Integer sizeMapAfter = getMap();

        assertEquals(sizeMapBefore, sizeMapAfter);
    }

    private int getMap() {
        return ((UserStorage) context.getAttribute(USER_STORAGE)).getUserStorageMap().size();
    }

    private void initRequestUserReg() {
        when(request.getParameter(USERNAME)).thenReturn(userReg.getUsername());
        when(request.getParameter(EMAIL)).thenReturn(userReg.getEmail());
        when(request.getParameter(PASSWORD)).thenReturn(userReg.getPassword());
        when(request.getParameter(PASSWORD_REP)).thenReturn(userReg.getPassword());
        when(request.getParameter(NEWS)).thenReturn(String.valueOf(userReg.getNews()));
        when(request.getParameter(REAL_NAME)).thenReturn(userReg.getName());
        when(request.getParameter(SURNAME)).thenReturn(userReg.getSurname());
        when(request.getParameter(CAPTCHA_KEY)).thenReturn(userReg.getCaptchaCode());
        when(request.getParameter(CAPTCHA_HIDDEN)).thenReturn(userReg.getHiddenCaptcha());
    }

    private void initUserReg() {
        userReg.setUsername(USER_FIELD);
        userReg.setEmail(EMAIL_USER);
        userReg.setPassword(USER_FIELD);
        userReg.setPasswordRep(USER_FIELD);
        userReg.setName(USER_FIELD);
        userReg.setSurname(USER_FIELD);
        userReg.setCaptchaCode(USER_FIELD);
        userReg.setHiddenCaptcha(USER_FIELD);
        userReg.setNews(false);
    }
}