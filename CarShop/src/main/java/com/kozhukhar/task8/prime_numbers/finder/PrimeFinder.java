package com.kozhukhar.task8.prime_numbers.finder;

import com.kozhukhar.task8.prime_numbers.FindPrimeNum;
import com.kozhukhar.task8.prime_numbers.initializer.ThreadInitialize;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class PrimeFinder {

    public abstract List<Integer> find(Integer countOfThread, Integer from, Integer frequency, Integer lastFrequency) throws InterruptedException;

    void findByThread(
            Integer countOfThread, Integer from,
            Integer frequency, Integer lastFrequency,
            FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers, ThreadInitialize threadInitialize
    ) {
        Thread[] threads = new Thread[countOfThread];
        threadInitialize.initializeThread(countOfThread, from, frequency, lastFrequency, threads, findPrimeNum, primeNumbers);

        threadsStart(threads);
        threadsJoin(threads);
    }

    void findByExecutor(
            Integer countOfThread, Integer from,
            Integer frequency, Integer lastFrequency,
            FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers, ThreadInitialize threadInitialize
    ) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(countOfThread);
        threadInitialize.initializeExecutor(countOfThread, from, frequency, lastFrequency, service, findPrimeNum, primeNumbers);
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
    }

    void threadsStart(Thread[] threads) {
        Arrays.stream(threads).forEach((Thread::start));
    }

    void threadsJoin(Thread[] threads) {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
