package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.File;

public class FileBySizeDiapasonSearcher implements FileSearchChain {

    private FileSearchChain fileSearchChain;

    private Integer sizeFrom;

    private Integer sizeTo;

    public FileBySizeDiapasonSearcher(FileSearchChain fileSearchChain, Integer sizeFrom, Integer sizeTo) {
        this.fileSearchChain = fileSearchChain;
        this.sizeFrom = sizeFrom;
        this.sizeTo = sizeTo;
    }

    @Override
    public boolean search(File file) throws AppException {
        try {
            if (file.length() >= sizeFrom && file.length() <= sizeTo) return true;
            return fileSearchChain != null && !fileSearchChain.search(file);
        } catch (Exception ex) {
            throw new AppException(Messages.SEARCH_BY_SIZE_DIAPASON_EXCEPTION, ex);
        }
    }
}
