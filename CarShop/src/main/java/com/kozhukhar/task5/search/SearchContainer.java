package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.InputStream;
import java.util.Scanner;

public class SearchContainer {

    private Scanner scanner;

    private String input;

    public SearchContainer(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public SearchContainer() {
        scanner = new Scanner(System.in);
    }

    public FileSearchChain searchByName(FileSearchChain parentChain) throws AppException {
        try {
            System.out.println("> Search files by names? (0\\1)");
            input = scanner.nextLine();
            if (Integer.parseInt(input) == 1) {
                System.out.println("> Input file name : ");
                input = scanner.nextLine();
                return new FileByNameSearcher(parentChain, input);
            }
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_CREATE_FILTER_BY_NAME, ex);
        }
        return parentChain;
    }

    public FileSearchChain searchByExtension(FileSearchChain parentChain) throws AppException {
        try {
            System.out.println("> Search files by extension? (0\\1)");
            input = scanner.nextLine();
            if (Integer.parseInt(input) == 1) {
                System.out.println(">> Input file extension : ");
                input = scanner.nextLine();
                return new FileByExtensionSearcher(parentChain, input);
            }
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_CREATE_FILTER_BY_EXTENSION, ex);
        }
        return parentChain;
    }

    public FileSearchChain searchBySize(FileSearchChain parentChain) throws AppException {
        try {
            System.out.println("> Search files by size diapason? (0\\1)");
            input = scanner.nextLine();
            if (Integer.parseInt(input) == 1) {
                System.out.println(">> Input size from (bytes): ");
                Integer sizeFrom = Integer.parseInt(scanner.nextLine());
                System.out.println(">> Input size up (bytes): ");
                Integer sizeTo = Integer.parseInt(scanner.nextLine());
                return new FileBySizeDiapasonSearcher(parentChain, sizeFrom, sizeTo);
            }
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_CREATE_FILTER_BY_SIZE_DIAPASON, ex);
        }
        return parentChain;
    }

    public FileSearchChain searchByDate(FileSearchChain parentChain) throws AppException {
        try {
            System.out.println("> Search files by date changes diapason? (0\\1)");
            input = scanner.nextLine();
            if (Integer.parseInt(input) == 1) {
                System.out.println(">> Input date from : ");
                String dateFrom = scanner.nextLine();
                System.out.println(">> Input date to : ");
                String dateTo = scanner.nextLine();
                return new FileByDateChangeSearcher(parentChain, dateFrom, dateTo);
            }
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_CREATE_FILTER_BY_DATE_DIAPASON, ex);
        }
        return parentChain;
    }
}
