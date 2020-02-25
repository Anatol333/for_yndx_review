package com.kozhukhar.carshop_online.web.service;

import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.AppException;

public interface UserService {

    User loginUser(User user) throws AppException;

    void saveUser(User user) throws AppException;

    void banUser(String username, Integer banTime) throws AppException;
}
