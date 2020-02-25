package com.kozhukhar.carshop_online.web.service.offline;

import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.db.storage.UserStorage;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.exception.UserExistsException;

public class OfflineUserService {

    private UserStorage userStorage;

    public OfflineUserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public String loginUser(User user) throws UserExistsException {
        if (!userStorage.mapExists(user)) {
            throw new UserExistsException(Messages.USERNAME_OR_PASSWORD_ARE_WRONG);
        }
        return Messages.USER_LOGGED_SUCCESSFULLY;
    }

    public boolean addUser(User userEntity) throws UserExistsException {
        userStorage.create(userEntity);
        return true;
    }
}
