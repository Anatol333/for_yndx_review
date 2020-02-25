package com.kozhukhar.carshop_online.db.dao;

import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.exception.DBException;

import java.util.List;

public interface ProductDao<T> {

    List<T> getPage(int pageLength, int pageNum) throws DBException;

    List<T> getAll() throws DBException;

    List<T> getAllByQuery(String query) throws DBException;

    Product get(Integer id) throws DBException;

}
