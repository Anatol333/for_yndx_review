package com.kozhukhar.carshop_online.db.dao;

import com.kozhukhar.carshop_online.exception.DBException;

import java.util.List;

public interface CategoriesDao<T> {
    List<T> getAll() throws DBException;
}
