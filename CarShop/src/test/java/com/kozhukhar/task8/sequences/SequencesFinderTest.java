package com.kozhukhar.task8.sequences;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class SequencesFinderTest {

    private static final String MONITOR_WAITING = "WAITING";
    private static final String FILE_PATH = "task8_example\\file.txt";
    private static final int MILLISECONDS = 50;
    private static final Integer MAX_LENGTH = 9;
    private static final String WRONG_FILE_PATH = "wrong file";
    private static final String INDEX_OUTPUT = "10, 32";

    @Test
    public void isSequenceFinderWaitingForFile() throws InterruptedException {
        SequencesFinder sequencesFinder = new SequencesFinder();
        Thread thread = new Thread(sequencesFinder);
        thread.start();
        Thread.sleep(MILLISECONDS);
        assertEquals(MONITOR_WAITING, thread.getState().toString());
    }

    @Test
    public void findLongestSequencesByteInFile() throws InterruptedException {
        SequencesFinder sequencesFinder = new SequencesFinder();
        Thread thread = new Thread(sequencesFinder);
        thread.start();
        Thread.sleep(MILLISECONDS);
        sequencesFinder.setFileName(FILE_PATH);
        assertTrue(sequencesFinder.isFindingStatus());
        Thread.sleep(MILLISECONDS);
        Integer max = sequencesFinder.getMaxSequence().size();

        assertEquals(MAX_LENGTH, sequencesFinder.getMaxCountOfByte());
        assertEquals(MAX_LENGTH, max);
        assertEquals(INDEX_OUTPUT, sequencesFinder.getIndexesOfSequence());
        assertFalse(sequencesFinder.isFindingStatus());
    }

    @Test
    public void sequenceThreadInterrupt() {
        SequencesFinder sequencesFinder = new SequencesFinder();
        Thread thread = new Thread(sequencesFinder);
        thread.start();
        thread.interrupt();

        assertTrue(thread.isInterrupted());
    }

    @Test
    public void wrongFileNameTest() throws InterruptedException {
        SequencesFinder sequencesFinder = new SequencesFinder();
        Thread thread = new Thread(sequencesFinder);
        thread.start();
        Thread.sleep(MILLISECONDS);
        sequencesFinder.setFileName(WRONG_FILE_PATH);

        assertNull(sequencesFinder.getMaxCountOfByte());
    }

}