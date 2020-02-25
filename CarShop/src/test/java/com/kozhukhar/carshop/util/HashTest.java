package com.kozhukhar.carshop.util;

import com.kozhukhar.carshop.entity.Transport;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class HashTest {

    private HashType stringLengthHash;

    private HashType sumStringHash;

    private static final Transport[] INSERT_TRANSPORT = {
            new Transport("car", "car", 2),
            new Transport("transp2", "transp2", 3),
            new Transport("testThree", "testThree", 1),
    };

    private static final String[] KEYS = {
            INSERT_TRANSPORT[0].getName() + INSERT_TRANSPORT[0].getModel(),
            INSERT_TRANSPORT[1].getName() + INSERT_TRANSPORT[1].getModel(),
            INSERT_TRANSPORT[2].getName() + INSERT_TRANSPORT[2].getModel(),
    };

    @Before
    public void setUp() throws Exception {
        stringLengthHash = String::length;
        sumStringHash = str -> {
            int ascii = 0;
            for (int i = 0; i < Math.min(str.length(), 4); ++i) {
                ascii += str.charAt(i);
            }
            return ascii;
        };
    }

    @Test
    public void hashCodeElementByStringLengthHashMap() {
        HashMap<HashWrapper, Transport> hashMap = new HashMap<>();
        hashMap.put(new HashWrapper(KEYS[0],stringLengthHash), INSERT_TRANSPORT[0]);
        hashMap.put(new HashWrapper(KEYS[1],stringLengthHash), INSERT_TRANSPORT[1]);
        hashMap.put(new HashWrapper(KEYS[2],stringLengthHash), INSERT_TRANSPORT[2]);
        hashMap.forEach((k, v) -> System.out.println(k + "," + v));
    }

    @Test
    public void hashCodeElementBySumHashMap() {
        HashMap<HashWrapper, Transport> hashMap = new HashMap<>();
        hashMap.put(new HashWrapper(KEYS[0],sumStringHash), INSERT_TRANSPORT[0]);
        hashMap.put(new HashWrapper(KEYS[1],sumStringHash), INSERT_TRANSPORT[1]);
        hashMap.put(new HashWrapper(KEYS[2],sumStringHash), INSERT_TRANSPORT[2]);
        hashMap.forEach((k, v) -> System.out.println(k + "," + v));
    }

    @Test
    public void hashCodeElementByStringLengthLinkedHashMap() {
        LinkedHashMap<HashWrapper, Transport> hashMap = new LinkedHashMap<>();
        hashMap.put(new HashWrapper(KEYS[0],stringLengthHash), INSERT_TRANSPORT[0]);
        hashMap.put(new HashWrapper(KEYS[1],stringLengthHash), INSERT_TRANSPORT[1]);
        hashMap.put(new HashWrapper(KEYS[2],stringLengthHash), INSERT_TRANSPORT[2]);
        hashMap.forEach((k, v) -> System.out.println(k + "," + v));
    }

    @Test
    public void hashCodeElementBySumLinkedHashMap() {
        LinkedHashMap<HashWrapper, Transport> hashMap = new LinkedHashMap<>();
        hashMap.put(new HashWrapper(KEYS[0],sumStringHash), INSERT_TRANSPORT[0]);
        hashMap.put(new HashWrapper(KEYS[1],sumStringHash), INSERT_TRANSPORT[1]);
        hashMap.put(new HashWrapper(KEYS[2],sumStringHash), INSERT_TRANSPORT[2]);
        hashMap.forEach((k, v) -> System.out.println(k + "," + v));
    }

}