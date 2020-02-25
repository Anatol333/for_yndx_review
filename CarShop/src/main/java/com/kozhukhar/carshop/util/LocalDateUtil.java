package com.kozhukhar.carshop.util;

import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateUtil {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

    public static LocalDateTime dateFormatter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDateTime dateCompare(LocalDateTime lowerDate, LocalDateTime higherDate) throws AppException {
        if (lowerDate == null && higherDate == null) {
            throw new AppException(Messages.DATE_WAS_NOT_FOUND);
        }
        if (lowerDate == null) {
            return higherDate;
        }
        if (higherDate == null) {
            return lowerDate;
        }
        return lowerDate.compareTo(higherDate) <= 0 ? lowerDate : higherDate;
    }

}
