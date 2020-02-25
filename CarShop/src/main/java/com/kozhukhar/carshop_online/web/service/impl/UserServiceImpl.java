package com.kozhukhar.carshop_online.web.service.impl;

import com.kozhukhar.carshop_online.db.dao.RoleDao;
import com.kozhukhar.carshop_online.db.dao.impl.UserDaoImpl;
import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.db.storage.Roles;
import com.kozhukhar.carshop_online.db.transact.JdbcTransactionManager;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.exception.UserExistsException;
import com.kozhukhar.carshop_online.web.service.UserService;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {

    private JdbcTransactionManager transactionManager;

    private UserDaoImpl userDao;

    private RoleDao roleDao;

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(JdbcTransactionManager transactionManager, UserDaoImpl userDao, RoleDao roleDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loginUser(User user) throws AppException {
        return transactionManager.execute(() -> {
            User loggedUser;
            try {
                loggedUser = userDao.get(user.getUsername());

                if (null == loggedUser || !user.getUsername().equals(loggedUser.getUsername())
                        || !user.getPassword().equals(loggedUser.getPassword())) {
                    throw new UserExistsException(Messages.USERNAME_OR_PASSWORD_ARE_WRONG);
                }
                if (loggedUser.getBan()) {
                    throw new AppException(Messages.USER_WAS_BANNED.toString());
                }
            } catch (DBException ex) {
                throw new AppException(Messages.DB_FAILED_LOGIN);
            }

            return loggedUser;
        });
    }

    @Override
    public void saveUser(User userEntity) throws AppException {
        transactionManager.execute(() -> {
            try {
                userDao.save(userEntity);
                User user = userDao.get(userEntity.getUsername());
                roleDao.addRoleToUser(user.getId(), Roles.USER.getName());

            } catch (DBException ex) {
                throw new AppException(Messages.DB_USER_NOT_REGISTERED);
            }
            return userEntity;
        });
    }

    @Override
    public void banUser(String username, Integer banTime) throws AppException {
        transactionManager.execute(() -> {
            User existedUser;
            try {
                existedUser = userDao.get(username);
                if (existedUser != null) {
                    new Thread(() -> {
                        try {
                            userDao.banUser(existedUser.getId(), true);
                            Thread.sleep(banTime);
                            userDao.banUser(existedUser.getId(), false);
                        } catch (DBException | InterruptedException ex) {
                            LOG.error(Messages.CANNOT_BAN_USER + ex.getMessage());
                        }
                    }).start();
                }
            } catch (DBException ex) {
                throw new AppException(Messages.DB_USER_NOT_REGISTERED);
            }
            return existedUser;
        });
    }
}
