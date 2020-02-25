package com.kozhukhar.task8.prime_numbers.finder;

import com.kozhukhar.task8.prime_numbers.FindPrimeNum;
import com.kozhukhar.task8.prime_numbers.initializer.ThreadInitialize;
import com.kozhukhar.task8.prime_numbers.initializer.ThreadInitializeDifferent;

import java.util.ArrayList;
import java.util.List;

public class FinderByExecutorInit extends PrimeFinder {

    @Override
    public List<Integer> find(Integer countOfThread, Integer from, Integer frequency, Integer lastFrequency) throws InterruptedException {
        List<Integer> primeNumbers = new ArrayList<>();
        FindPrimeNum[] findPrimeNum = new FindPrimeNum[countOfThread];
        ThreadInitialize threadInitialize = new ThreadInitializeDifferent();
        findByExecutor(countOfThread, from, frequency, lastFrequency, findPrimeNum, primeNumbers, threadInitialize);
        for (FindPrimeNum findPrime : findPrimeNum) {
            primeNumbers.addAll(findPrime.getNumbers());
        }
        return primeNumbers;
    }
}
