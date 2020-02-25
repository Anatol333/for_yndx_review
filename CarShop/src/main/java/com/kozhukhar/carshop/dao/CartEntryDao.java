package com.kozhukhar.carshop.dao;

import com.kozhukhar.carshop_online.exception.DBException;

import java.util.List;

public interface CartEntryDao<T> {


    /**
     * Get all objects "T".
     *
     * @return List<T>
     * @throws DBException
     */
    List<T> getAll() throws DBException;

    /**
     * Save new object.
     *
     * @param t
     * @throws DBException
     */
    void save(T t) throws DBException;


}
