package com.kozhukhar.carshop.creator.reflection;

import java.util.Scanner;

public class ConsoleReflectionCreator implements ReflectionCreator {

    private Scanner scanner;

    public ConsoleReflectionCreator() {
        scanner = new Scanner(System.in);
    }

    public ConsoleReflectionCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Integer getInt(String msg) {
        System.out.println(msg);
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public String getString(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }
}
