package com.kozhukhar.carshop_online.db.dao;

import com.kozhukhar.carshop_online.exception.DBException;

public interface Dao<T> {

    T get(String username) throws DBException;

    void save(T t) throws DBException;

    void banUser(Integer userId, Boolean ban) throws DBException;

}
