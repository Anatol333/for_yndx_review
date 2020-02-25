package com.kozhukhar.task8.prime_numbers;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.task8.prime_numbers.finder.CommonFinderByExecutor;
import com.kozhukhar.task8.prime_numbers.finder.CommonFinderByThreadInit;
import com.kozhukhar.task8.prime_numbers.finder.FinderByExecutorInit;
import com.kozhukhar.task8.prime_numbers.finder.FinderByThreadInit;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Task8Main {

    private static final Logger LOG = Logger.getLogger(Task8Main.class);

    public static void main(String[] args) {
        try {
            initApplication();
        } catch (AppException ex) {
            LOG.error(ex.getMessage());
        } catch (InterruptedException ex) {
            LOG.error(Messages.CRITICAL_ERROR_IN_APPLICATION);
        }
    }

    private static void initApplication() throws InterruptedException, AppException {
        int diapasonFrom;
        int diapasonTo;
        int countOfThreads;
        int variantExecute;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input interval for finding prime numbers. ");
        System.out.print("Diapason from : ");
        diapasonFrom = inputIntAmount(scanner);
        System.out.print("Diapason to : ");
        diapasonTo = inputIntAmount(scanner);
        System.out.println("Input count of threads for finding prime : ");
        countOfThreads = inputIntAmount(scanner);
        System.out.println("Choose thread variant :\n1 - Threads;\n2 - Executors;");
        variantExecute = inputIntAmount(scanner);

        List<Integer> primeNumbersArray;
        List<Integer> primeNumbersSynchronized;

        int frequency = findFrequency(countOfThreads, diapasonFrom, diapasonTo);
        int lastFrequency = findLastFrequency(frequency, countOfThreads, diapasonFrom, diapasonTo);

        if (variantExecute == 1) {
            primeNumbersArray = new FinderByThreadInit().find(countOfThreads, diapasonFrom, frequency, lastFrequency);
            primeNumbersSynchronized = new CommonFinderByThreadInit().find(countOfThreads, diapasonFrom, frequency, lastFrequency);
        } else {
            primeNumbersArray = new FinderByExecutorInit().find(countOfThreads, diapasonFrom, frequency, lastFrequency);
            primeNumbersSynchronized = new CommonFinderByExecutor().find(countOfThreads, diapasonFrom, frequency, lastFrequency);
        }

        System.out.println("Prime numbers in diapason :\n" + primeNumbersArray);
        System.out.println("Prime numbers in diapason with common list :\n" + primeNumbersSynchronized);
    }

    private static Integer inputIntAmount(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                LOG.error(Messages.WRONG_INPUT_FORMAT_TRY_AGAIN);
                input.nextLine();
            }
        }
    }

    private static void valueCheck(int from, int to) throws AppException {
        if (to < from) {
            throw new AppException(Messages.SECOND_VALUE_WRONG);
        }
    }

    private static Integer findFrequency(int countOfThread, int from, int to) throws AppException {
        valueCheck(from, to);
        return (to - from) / countOfThread;
    }

    private static Integer findLastFrequency(int frequency, int countOfThread, int from, int to) throws AppException {
        valueCheck(from, to);

        int lastFrequency = 0;
        if (frequency * countOfThread < to - from) {
            lastFrequency = frequency + to - from - (frequency * countOfThread);
        }

        return lastFrequency;
    }

}
