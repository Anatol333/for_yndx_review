package com.kozhukhar.task8.prime_numbers;

import java.util.ArrayList;
import java.util.List;

public class FindPrimeNum implements Runnable {

    private List<Integer> numbers;

    private Integer from;

    private Integer to;

    private Integer localFrequency;

    public FindPrimeNum(Integer from, Integer to) {
        this.from = from;
        this.to = to;
        this.numbers = new ArrayList<>();
    }

    public FindPrimeNum(List<Integer> numbers, Integer from, Integer to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    public FindPrimeNum(Integer from, Integer to, Integer localFrequency) {
        this.from = from;
        this.to = to;
        this.localFrequency = localFrequency;
        this.numbers = new ArrayList<>();
    }

    public FindPrimeNum(List<Integer> numbers, Integer from, Integer to, Integer localFrequency) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
        this.localFrequency = localFrequency;
    }

    @Override
    public void run() {
        if (localFrequency != null) {
            findPrimeNumbers();
        } else {
            findAllPrimeNumbers();
        }
    }

    private void findAllPrimeNumbers() {
        for (int i = from; i < to; ++i) {
            if (isPrimeNumber(i)) {
                numbers.add(i);
            }
        }
    }

    private void findPrimeNumbers() {
        int num = from;
        for (int i = 0; i < to; ++i) {
            if (isPrimeNumber(num)) {
                numbers.add(num);
            }
            num += localFrequency;
        }
    }

    private boolean isPrimeNumber(Integer num) {
        if (num <= 1) return false;

        boolean prime = true;
        int i = 2;

        while (i <= num / 2 && prime) {
            if (num % i == 0) {
                prime = false;
            }
            ++i;
        }
        return prime;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
