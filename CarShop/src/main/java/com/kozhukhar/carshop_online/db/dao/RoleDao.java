package com.kozhukhar.carshop_online.db.dao;

import com.kozhukhar.carshop_online.exception.DBException;

public interface RoleDao {

    void addRoleToUser(Integer userId, String role) throws DBException;

}
