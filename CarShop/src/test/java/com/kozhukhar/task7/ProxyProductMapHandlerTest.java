package com.kozhukhar.task7;

import com.kozhukhar.task7.map_handl.ProxyProductMapHandler;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class ProxyProductMapHandlerTest {

    private ClassLoader classLoader;

    private Class<?>[] interfaces;

    private ProxyProductMapHandler invocationHandler;

    private static final String TEST_NAME = "testName";

    @Before
    public void setUp() {
        Product productObject = new ProductImpl();
        classLoader = productObject.getClass().getClassLoader();
        interfaces = productObject.getClass().getInterfaces();
        invocationHandler = new ProxyProductMapHandler(productObject);
    }

    @Test
    public void testInvokeProxyMethodWithGet() {
        Product reader = (Product) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        reader.setName(TEST_NAME);
        assertEquals(TEST_NAME, reader.getName().substring(1, TEST_NAME.length() + 1));
    }
}
