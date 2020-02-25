package com.kozhukhar.task8.sequences;

import java.util.Scanner;

public class FileFinderMain {

    private static final String TASK8_PATH = "task8_example\\";

    public static void main(String[] args) {
        SequencesFinder sequencesFinder = new SequencesFinder();
        Thread threadSequence = new Thread(sequencesFinder);
        threadSequence.start();

        while (true) {
            start(sequencesFinder);
            while (sequencesFinder.isFindingStatus()) {
                System.out.println("Current length in the sequence -> " + sequencesFinder.getMaxCountOfByte());
            }
            System.out.println("~~~~~~~~~~~~~~~~~~Result~~~~~~~~~~~~~~~~~");
            System.out.println(sequencesFinder.getMaxSequence());
            System.out.println("Indexes in the sequence : " + sequencesFinder.getIndexesOfSequence());
        }

    }

    private static void start(SequencesFinder sequencesFinder) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file name: ");
        String fileName = scanner.nextLine();
        sequencesFinder.setFileName(TASK8_PATH + fileName);
    }

}
