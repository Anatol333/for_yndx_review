package com.kozhukhar.carshop.anotation;

import com.kozhukhar.carshop.creator.reflection.ModeInitializer;
import com.kozhukhar.carshop.creator.reflection.ReflectionCreator;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.util.LocaleMessageUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CreatorAnnotation {

    private String localizedMessage;

    private LocaleMessageUtil localeMessageUtil;

    private Boolean mode;

    private String typeOfTransport;

    private ReflectionCreator creator;

    private ModeInitializer modeInitializer;


    public CreatorAnnotation() {
        this.mode = false;
        this.modeInitializer = new ModeInitializer();
        this.localeMessageUtil = new LocaleMessageUtil();
        localeMessageUtil.setLocale("ru");
    }

    public void regObject(Object... objectArray) {
        try {
            for (Object object : objectArray) {
                Class classObject = object.getClass();

                Method[] declaredMethods = classObject.getMethods();
                for (Method method : declaredMethods) {
                    if (method.isAnnotationPresent(AnnotationLocalize.class)) {
                        Class[] parameterTypes = method.getParameterTypes();
                        for (Class clazz : parameterTypes) {
                            AnnotationLocalize annotation = method.getAnnotation(AnnotationLocalize.class);
                            localizedMessage = localeMessageUtil.localizeMessage(annotation.keyName());
                            creator = modeInitializer.getReflectionCreator(mode, typeOfTransport);
                            if (clazz != Integer.class) {
                                method.invoke(object, creator.getString(localizedMessage));
                            } else {
                                method.invoke(object, creator.getInt(localizedMessage));
                            }
                        }
                    }
                }
            }
        } catch (AppException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setLocaleMessageUtil(LocaleMessageUtil localeMessageUtil) {
        this.localeMessageUtil = localeMessageUtil;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    public void setType(String typeOfTransport) {
        this.typeOfTransport = typeOfTransport;
    }
}
