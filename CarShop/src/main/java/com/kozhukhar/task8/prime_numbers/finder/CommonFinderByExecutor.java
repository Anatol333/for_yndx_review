package com.kozhukhar.task8.prime_numbers.finder;

import com.kozhukhar.task8.prime_numbers.FindPrimeNum;
import com.kozhukhar.task8.prime_numbers.initializer.ThreadInitialize;
import com.kozhukhar.task8.prime_numbers.initializer.ThreadInitializeCommon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonFinderByExecutor extends PrimeFinder {
    @Override
    public List<Integer> find(Integer countOfThread, Integer from, Integer frequency, Integer lastFrequency) throws InterruptedException {
        List<Integer> primeNumbers = Collections.synchronizedList(new ArrayList<>());
        FindPrimeNum[] findPrimeNum = new FindPrimeNum[countOfThread];
        ThreadInitialize threadInitialize = new ThreadInitializeCommon();
        findByExecutor(countOfThread, from, frequency, lastFrequency, findPrimeNum, primeNumbers, threadInitialize);
        return primeNumbers;
    }
}
