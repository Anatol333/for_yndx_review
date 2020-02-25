package com.kozhukhar.task7.map_handl;

import com.kozhukhar.task7.Product;
import com.kozhukhar.task7.ProductImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProxyProductMapHandler implements InvocationHandler {

    private Product product;

    private Map<String, Product> products;

    private static final String SET = "set";
    private static final String GET = "get";

    public ProxyProductMapHandler(Product product) {
        this.product = product;
        this.products = new HashMap<String, Product>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(GET)) {
            System.out.println(products.toString());
            return products.toString();
        }
        if (method.getName().startsWith(SET)) {
            String key = args[0].toString();
            if (!products.containsKey(key)) {
                product.setName(key);
                return products.put(key, product);
            }
        }
        return method.invoke(products, args);
    }
}
