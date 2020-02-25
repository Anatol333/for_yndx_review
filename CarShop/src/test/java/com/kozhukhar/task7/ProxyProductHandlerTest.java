package com.kozhukhar.task7;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class ProxyProductHandlerTest {

    private ClassLoader classLoader;

    private Class<?>[] interfaces;

    private ProxyProductHandler invocationHandler;

    @Before
    public void setUp() {
        Product productObject = new ProductImpl();
        classLoader = productObject.getClass().getClassLoader();
        interfaces = productObject.getClass().getInterfaces();
        invocationHandler = new ProxyProductHandler(productObject);
    }

    @Test(expected = Exception.class)
    public void testInvokeProxyMethodWithSet() {
        Product reader = (Product) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        reader.setName(null);
    }

    @Test
    public void testInvokeProxyMethod() {
        Product reader = (Product) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        assertEquals("Test name", reader.getName());
    }

}