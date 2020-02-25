package com.kozhukhar.carshop_online.exception;

public class UserValidateException extends AppException {

    public UserValidateException() {
        super();
    }

    public UserValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserValidateException(String message) {
        super(message);
    }
}
