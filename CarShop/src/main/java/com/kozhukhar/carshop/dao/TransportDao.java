package com.kozhukhar.carshop.dao;

import com.kozhukhar.carshop_online.exception.DBException;

import java.util.List;

public interface TransportDao<T> {


    /**
     * Get Object By name value.
     *
     * @param name
     * @return
     */
    T get(String name) throws DBException;

    /**
     * Get all objects "T".
     *
     * @return List<T>
     * @throws DBException
     */
    List<T> getAll() throws DBException;


}
