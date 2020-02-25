package com.kozhukhar.task8.prime_numbers.initializer;

import com.kozhukhar.task8.prime_numbers.FindPrimeNum;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class ThreadInitializeDifferent implements ThreadInitialize {
    @Override
    public void initializeThread(
            int countOfThread, int from,
            Integer frequency, Integer lastFrequency,
            Thread[] threads, FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers
    ) {
        Integer temporaryFrom = from;
        for (int i = 0; i < countOfThread - 1; ++i) {
            findPrimeNum[i] = new FindPrimeNum(i + from, frequency, countOfThread);
            threads[i] = new Thread(findPrimeNum[i]);
            temporaryFrom += frequency;
        }
        findPrimeNum[countOfThread - 1] = new FindPrimeNum(temporaryFrom, temporaryFrom + lastFrequency);
        threads[countOfThread - 1] = new Thread(findPrimeNum[countOfThread - 1]);
    }

    @Override
    public void initializeExecutor(
            int countOfThread, int from,
            Integer frequency, Integer lastFrequency,
            ExecutorService service, FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers
    ) {
        Integer temporaryFrom = from;
        for (int i = 0; i < countOfThread - 1; ++i) {
            findPrimeNum[i] = new FindPrimeNum(i + from, frequency, countOfThread);
            service.submit(findPrimeNum[i]);
            temporaryFrom += frequency;
        }
        findPrimeNum[countOfThread - 1] = new FindPrimeNum(temporaryFrom, temporaryFrom + lastFrequency);
        service.submit(findPrimeNum[countOfThread - 1]);
    }
}
