package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainFileCommanderTest {

    private static final String ENCODING = "Cp1251";

    private MainFileCommander mainFileCommander;

    @Before
    public void setUp() throws Exception {
        mainFileCommander = new MainFileCommander();
    }

    @Test(expected = AppException.class)
    public void allInputZero() throws AppException, UnsupportedEncodingException {
        mainFileCommander = new MainFileCommander(new ByteArrayInputStream(
                "0^0^0^0".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        mainFileCommander.startFiltering();
    }

    @Test
    public void findByFileName() throws AppException, UnsupportedEncodingException {
        mainFileCommander = new MainFileCommander(new ByteArrayInputStream(
                "1^test^0^0^0".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        mainFileCommander.startFiltering();
        assertTrue(mainFileCommander.getFileNames().stream().allMatch(el -> el.split("\\.")[0].equals("test")));
    }

    @Test
    public void findByExtension() throws AppException, UnsupportedEncodingException {
        mainFileCommander = new MainFileCommander(new ByteArrayInputStream(
                "0^1^txt^0^0^0".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        mainFileCommander.startFiltering();
        assertTrue(mainFileCommander.getFileNames().stream().allMatch(el -> el.split("\\.")[1].equals("txt")));
    }

    @Test
    public void findBySize() throws AppException, UnsupportedEncodingException {
        mainFileCommander = new MainFileCommander(new ByteArrayInputStream(
                "0^0^1^150000^180000^0^0^0".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        mainFileCommander.startFiltering();
        assertEquals(1, mainFileCommander.getFileNames().size());
        assertEquals("size", mainFileCommander.getFileNames().get(0));
    }
}