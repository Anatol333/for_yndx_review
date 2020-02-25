package com.kozhukhar.carshop.util;

import com.kozhukhar.carshop.entity.Car;
import com.kozhukhar.carshop.entity.Transport;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UniqueElementListTest {


    private static final int EQUALS_TWO = 2;
    private static final int WRONG_INDEX = -1;
    private static final int WRONG_INDEX_OVER = 9999;

    private UniqueElementList<Transport> transports;

    private static final Transport[] INSERT_TRANSPORT = {
            new Transport("test1", "test1", 0),
            new Transport("test2", "test2", 0),
            new Transport("test3", "test3", 0),
    };
    private static final Transport[] INSERT_TRANSPORT_OTHER = {
            new Transport("test4", "test4", 0),
            new Transport("test5", "test5", 0),
            new Transport("test6", "test6", 0),
    };

    @Before
    public void setUp() throws Exception {
        transports = new UniqueElementList<>();
    }

    @Test(expected = IllegalStateException.class)
    public void addElementToList() {
        transports.add(INSERT_TRANSPORT[0]);
        transports.add(INSERT_TRANSPORT[0]);
        assertEquals(1, transports.size());
    }

    @Test(expected = IllegalStateException.class)
    public void addElementByIndexToList() {
        transports.add(0, INSERT_TRANSPORT[0]);
        transports.add(0, INSERT_TRANSPORT[0]);
        transports.add(0, INSERT_TRANSPORT[1]);
        assertEquals(EQUALS_TWO, transports.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addElementByIndexWithBigValue() {
        transports.add(WRONG_INDEX_OVER, new Transport());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addElementByIndexWithLessValue() {
        transports.add(WRONG_INDEX, new Transport());
    }

    @Test(expected = IllegalStateException.class)
    public void addAllElementsToList() {
        assertTrue(transports.addAll(Arrays.asList(INSERT_TRANSPORT)));
        assertFalse(transports.addAll(Arrays.asList(INSERT_TRANSPORT[0], INSERT_TRANSPORT[1], INSERT_TRANSPORT_OTHER[0])));
        assertEquals(INSERT_TRANSPORT.length, transports.size());
    }

    @Test(expected = IllegalStateException.class)
    public void addAllByIndex() {
        assertTrue(transports.addAll(0, Arrays.asList(INSERT_TRANSPORT)));
        assertFalse(transports.addAll(0, Arrays.asList(INSERT_TRANSPORT)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllByIndexWithBigValue() {
        transports.addAll(WRONG_INDEX_OVER, Arrays.asList(INSERT_TRANSPORT));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllByIndexWithLessValue() {
        transports.addAll(WRONG_INDEX, Arrays.asList(INSERT_TRANSPORT));
    }

    @Test(expected = IllegalStateException.class)
    public void addSimilarElementWhichTrue() {
        Transport equalObject = new Car(INSERT_TRANSPORT[0].getName(),
                INSERT_TRANSPORT[0].getModel(),
                null, null, null, null, null);
        assertTrue(transports.add(INSERT_TRANSPORT[0]));
        assertFalse(transports.add(INSERT_TRANSPORT[0]));
        assertTrue(transports.add(equalObject));
        assertEquals(EQUALS_TWO, transports.size());
    }
}