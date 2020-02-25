package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.File;

public class FileByExtensionSearcher implements FileSearchChain {

    private FileSearchChain fileSearchChain;

    private String extension;


    public FileByExtensionSearcher(FileSearchChain fileSearchChain, String extension) {
        this.fileSearchChain = fileSearchChain;
        this.extension = extension;
    }

    @Override
    public boolean search(File file) throws AppException {
        try {
            if (file.getName().endsWith(extension)) return true;
            return fileSearchChain != null && !fileSearchChain.search(file);
        } catch (Exception ex) {
            throw new AppException(Messages.SEARCH_BY_EXTENSION_EXCEPTION, ex);
        }
    }
}
