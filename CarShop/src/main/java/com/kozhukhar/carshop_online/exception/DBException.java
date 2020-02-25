package com.kozhukhar.carshop_online.exception;

/**
 * An exception that provides information on a database access error.
 *
 * @author Anatol Kozhukhar
 */
public class DBException extends AppException {

    public DBException(String userIsAlreadyExists) {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
