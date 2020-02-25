package com.kozhukhar.carshop_online.db.transact;

import com.kozhukhar.carshop_online.exception.AppException;

import java.util.concurrent.Callable;

public interface TransactionManager {

    <T> T execute(Callable<T> callable) throws AppException;

}
