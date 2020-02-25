package com.kozhukhar.carshop_online.exception;

public class UserExistsException extends AppException {

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(String message) {
        super(message);
    }
}
