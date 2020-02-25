package com.kozhukhar.task8.prime_numbers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindPrimeNumTest {

    @Test
    public void createThreadFindPrimeNumbers() throws InterruptedException {
        FindPrimeNum findPrimeNum = new FindPrimeNum(0, 10);
        Thread finder = new Thread(findPrimeNum);
        finder.start();
        finder.join();
        assertEquals(4, findPrimeNum.getNumbers().size());
    }

}