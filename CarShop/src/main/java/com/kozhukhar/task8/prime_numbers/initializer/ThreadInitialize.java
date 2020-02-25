package com.kozhukhar.task8.prime_numbers.initializer;

import com.kozhukhar.task8.prime_numbers.FindPrimeNum;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface ThreadInitialize {

    void initializeThread(int countOfThread, int from,
                          Integer frequency, Integer lastFrequency,
                          Thread[] threads, FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers);

    void initializeExecutor(int countOfThread, int from,
                            Integer frequency, Integer lastFrequency,
                            ExecutorService service, FindPrimeNum[] findPrimeNum, List<Integer> primeNumbers);

}
