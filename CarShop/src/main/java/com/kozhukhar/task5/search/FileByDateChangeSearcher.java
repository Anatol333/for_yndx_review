package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class FileByDateChangeSearcher implements FileSearchChain {

    private FileSearchChain fileSearchChain;

    private String dateChangeFrom;

    private String dateChangeTo;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public FileByDateChangeSearcher(FileSearchChain fileSearchChain, String dateChangeFrom, String dateChangeTo) {
        this.fileSearchChain = fileSearchChain;
        this.dateChangeFrom = dateChangeFrom;
        this.dateChangeTo = dateChangeTo;
    }

    @Override
    public boolean search(File file) throws AppException {
        try {
            long dateFrom = getTimeSecond(dateChangeFrom);
            long dateTo = getTimeSecond(dateChangeTo);
            long fileDate = getTimeSecond(
                    new SimpleDateFormat(DATE_FORMAT).format(file.lastModified())
            );

            if (fileDate >= dateFrom && file.lastModified() <= dateTo) return false;
            return fileSearchChain != null && !fileSearchChain.search(file);
        } catch (Exception ex) {
            throw new AppException(Messages.SEARCH_BY_DATE_EXCEPTION, ex);
        }
    }

    private long getTimeSecond(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
    }
}
