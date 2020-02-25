package com.kozhukhar.task5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

/**
 * File Reader
 * with constructor which takes file name parameter.
 */
public class MyFileReader implements Iterable {

    private Scanner reader;

    public MyFileReader(String fileName) throws FileNotFoundException {
        reader = new Scanner(new FileReader(fileName));
    }

    @Override
    public Iterator iterator() {
        return new FileReaderIterator();
    }

    private class FileReaderIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return reader.hasNextLine();
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return reader.nextLine();
            }
            throw new IllegalStateException();
        }
    }
}
