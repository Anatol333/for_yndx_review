package com.kozhukhar.carshop_online.util.user_loader;

import com.kozhukhar.carshop_online.exception.AppException;

public interface Command {

    void init(Object object, String value) throws AppException;

}
