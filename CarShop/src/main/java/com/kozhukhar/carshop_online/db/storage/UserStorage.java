package com.kozhukhar.carshop_online.db.storage;

import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.exception.UserExistsException;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    private Map<Integer, User> userStorageMap;

    private static final User[] USERS = {
            new User("qwerty", "qwerty", "qwerty@email.com", true),
            new User("test", "test", "test@email.com", true),
            new User("test1", "test1", "test1@email.com", true),
            new User("test2", "test2", "test2@email.com", true)
    };

    public UserStorage() throws UserExistsException {
        userStorageMap = new HashMap<>();
        initUsers();
    }

    private void initUsers() throws UserExistsException {
        for (User user : USERS) {
            create(user);
        }
    }

    public void create(User user) throws UserExistsException {
        if (mapExists(user)) {
            throw new UserExistsException(Messages.USER_IS_ALREADY_EXISTS);
        }
        userStorageMap.put(userStorageMap.size(), user);
    }

    public boolean emailExists(String email) {
        return userStorageMap.values()
                .stream()
                .anyMatch(usr -> usr.getEmail().equals(email));
    }

    public boolean mapExists(User user) {
        return userStorageMap.containsValue(user);
    }

    public Map<Integer, User> getUserStorageMap() {
        return userStorageMap;
    }
}
