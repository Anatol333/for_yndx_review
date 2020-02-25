package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.File;

public class FileByNameSearcher implements FileSearchChain {

    private FileSearchChain fileSearchChain;

    private String fileName;

    public FileByNameSearcher(FileSearchChain fileSearchChain, String fileName) {
        this.fileSearchChain = fileSearchChain;
        this.fileName = fileName;
    }

    @Override
    public boolean search(File file) throws AppException {
        try {
            if (file.getName().matches(fileName + "\\..*")) return true;
            return fileSearchChain != null && !fileSearchChain.search(file);
        } catch (Exception ex) {
            throw new AppException(Messages.SEARCH_BY_NAME_EXCEPTION, ex);
        }
    }

}
