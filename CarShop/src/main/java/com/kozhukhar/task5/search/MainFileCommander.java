package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainFileCommander {

    private static final String PATH_TO_FILES = "task5_example\\";
    private List<String> fileNames;
    private FileSearchChain filterChain;
    private SearchContainer searchContainer;

    public MainFileCommander() {
        fileNames = new ArrayList<>();
        searchContainer = new SearchContainer();
    }

    public MainFileCommander(InputStream inputStream) {
        fileNames = new ArrayList<>();
        searchContainer = new SearchContainer(inputStream);
    }

    public void startFiltering() throws AppException {
        filterChain = searchContainer.searchByName(filterChain);
        filterChain = searchContainer.searchByExtension(filterChain);
        filterChain = searchContainer.searchBySize(filterChain);
        filterChain = searchContainer.searchByDate(filterChain);

        filtering(new File(PATH_TO_FILES));
    }

    private void filtering(File file) throws AppException {
        try {
            File[] filesArray = file.listFiles();
            for (File currentFile : filesArray) {
                if (currentFile.isDirectory()) {
                    filtering(currentFile);
                }
                if (filterChain.search(currentFile)) {
                    fileNames.add(currentFile.getName());
                    System.out.println(currentFile.getName());
                }
            }
        } catch (AppException ex) {
            throw new AppException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_FILTERING_FILES_EXCEPTION, ex);
        }
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
