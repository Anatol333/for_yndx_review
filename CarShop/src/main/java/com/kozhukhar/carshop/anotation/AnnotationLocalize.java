package com.kozhukhar.carshop.anotation;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationLocalize {

    String keyName();

}
