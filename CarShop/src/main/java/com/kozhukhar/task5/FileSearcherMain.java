package com.kozhukhar.task5;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.task5.search.MainFileCommander;

import java.io.IOException;

public class FileSearcherMain {

    private static final String PATH_FIRST_PART_TASK = "task5_example\\test.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("*********************TASK-5.1*********************");
        MyFileReader myFileReader = new MyFileReader(PATH_FIRST_PART_TASK);
        for (Object line : myFileReader) {
            System.out.println(line);
        }

        System.out.println("*********************TASK-5.2*********************");
        MainFileCommander mainFileCommander = new MainFileCommander();
        try {
            mainFileCommander.startFiltering();
            for (String fileName : mainFileCommander.getFileNames()) {
                System.out.println(fileName);
            }
        } catch (AppException ex) {
            ex.printStackTrace();
            System.out.print("Error - > ");
            System.out.println(ex.getMessage());
        }
    }

}
