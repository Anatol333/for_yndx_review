package com.kozhukhar.task8.sequences;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SequencesFinder implements Runnable {

    private Integer maxCountOfByte;

    private boolean findingStatus;

    private String file;

    private Integer firstIndex;

    private Integer secondIndex;

    private List<Byte> maxSequence;

    private static final Logger LOG = Logger.getLogger(SequencesFinder.class);

    private final Object SEQUENCE_MONITOR = new Object();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                LOG.info("Thread started and waiting for file.");
                synchronized (SEQUENCE_MONITOR) {
                    SEQUENCE_MONITOR.wait();
                }
                LOG.info("Starting finding by file name -> " + file);

                byte[] bytes = Files.readAllBytes(Paths.get(file));
                List<Byte> listBytes = new ArrayList<>();
                for (byte b : bytes) {
                    listBytes.add(b);
                }
                maxSequence = longestRepeatingSeq(listBytes);
                findingStatus = false;
            } catch (InterruptedException | IOException e) {
                findingStatus = false;
                LOG.error("Some trouble with file name!");
            }
        }
    }

    public void setFileName(String fileName) {
        findingStatus = true;
        file = fileName;
        synchronized (SEQUENCE_MONITOR) {
            SEQUENCE_MONITOR.notify();
        }
    }

    private List<Byte> longestRepeatingSeq(List<Byte> in) {
        firstIndex = -1;
        secondIndex = -1;

        int n = in.size();
        Set<List<Byte>> seen = new HashSet<>();
        List<Byte> max = Collections.emptyList();
        System.out.println(in.toArray().toString());
        for (int i = 0; i < n; i++) {
            for (int j = i + max.size() + 1; j <= n && j <= i + n / 2; j++) {
                maxCountOfByte = max.size();
                if (j == n || !in.get(j).equals(in.get(j - 1))) {
                    List<Byte> sub = in.subList(i, j);
                    if (seen.contains(sub)) {
                        if (sub.size() > max.size()) {
                            secondIndex = getSecondIndex(firstIndex, secondIndex, i);
                            firstIndex = getFirstIndex(firstIndex, i);
                            max = sub;
                        }
                    } else {
                        seen.add(sub);
                    }
                }
            }
        }
        System.out.println(firstIndex + ", " + secondIndex);
        return max;
    }

    private Integer getSecondIndex(Integer firstInd, Integer secondInd, Integer value) {
        return firstInd != -1 && secondInd == -1 ? value : secondInd;
    }

    private Integer getFirstIndex(Integer firstInd, Integer value) {
        return firstInd == -1 ? value : firstInd;
    }

    public String getIndexesOfSequence() {
        return firstIndex + ", " + secondIndex;
    }

    public Integer getMaxCountOfByte() {
        return maxCountOfByte;
    }

    public List<Byte> getMaxSequence() {
        return maxSequence;
    }

    public boolean isFindingStatus() {
        return findingStatus;
    }
}
