package com.kozhukhar.task7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyProductHandler implements InvocationHandler {

    private Product product;

    private static final String SET = "set";

    public ProxyProductHandler(Product product) {
        this.product = product;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith(SET)) throw new IllegalStateException();
        return method.invoke(product, args);
    }
}
