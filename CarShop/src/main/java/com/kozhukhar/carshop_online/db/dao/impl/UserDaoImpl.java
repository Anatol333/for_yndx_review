package com.kozhukhar.carshop_online.db.dao.impl;

import com.kozhukhar.carshop_online.db.Queries;
import com.kozhukhar.carshop_online.db.dao.Dao;
import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.db.transact.ConnectionHolder;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.DBUtil;
import com.kozhukhar.carshop_online.util.ResultSetConverter;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.ROLE_NAME;

public class UserDaoImpl implements Dao<User> {

    private DBUtil dbUtil;

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        this.dbUtil = new DBUtil();
    }

    @Override
    public User get(String username) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User newUser = null;

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_GET_USER_BY_USERNAME);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                newUser = new User();
                new ResultSetConverter().initUserField(rs, newUser);
                newUser.setRoles(getRolesByUsername(username));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_USER_BY_ID, ex);
        } finally {
            dbUtil.close(pstmt);
        }

        return newUser;
    }

    private List<String> getRolesByUsername(String username) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> roles = new ArrayList<>();

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_GET_USER_ROLES_BY_USERNAME);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roles.add(rs.getString(ROLE_NAME));
            }
        } catch (SQLException | DBException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_USER_BY_ID, ex);
        } finally {
            dbUtil.close(pstmt);
        }

        return roles;
    }

    @Override
    public void save(User user) throws DBException {

        PreparedStatement pstmt = null;
        try {
            User existedUser = get(user.getUsername());
            if (existedUser != null) {
                throw new DBException(Messages.USER_IS_ALREADY_EXISTS);
            }
            existedUser = getByEmail(user);
            if (existedUser != null) {
                throw new DBException(Messages.EMAIL_IS_EXIST_IN_THE_SYSTEM);
            }

            Connection conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_SAVE_NEW_USER);
            int atr = 1;
            pstmt.setString(atr++, user.getUsername());
            pstmt.setString(atr++, user.getPassword());
            pstmt.setString(atr++, user.getEmail());
            pstmt.setString(atr++, user.getName());
            pstmt.setString(atr++, user.getSurname());
            pstmt.setBoolean(atr, user.getNews());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(ex.getMessage(), ex);
        } finally {
            dbUtil.close(pstmt);
        }
    }

    public User getByEmail(User user) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User newUser = null;

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_GET_USER_BY_EMAIL);
            pstmt.setString(1, user.getEmail());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                newUser = new User();
                new ResultSetConverter().initUserField(rs, newUser);
            }
            conn.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.CANNOT_GET_USER_BY_EMAIL, ex);
        } finally {
            dbUtil.close(pstmt);
        }

        return newUser;
    }

    @Override
    public void banUser(Integer userId, Boolean ban) throws DBException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        User newUser = null;

        try {
            conn = ConnectionHolder.getConnection();
            pstmt = conn.prepareStatement(Queries.SQL_UPDATE_USER_BAN);
            int atr = 1;
            pstmt.setBoolean(atr++, ban);
            pstmt.setInt(atr, userId);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException | DBException ex) {
            LOG.error(ex.getMessage());
            throw new DBException(Messages.USER_WAS_BANNED.toString(), ex);
        } finally {
            dbUtil.close(pstmt);
        }
    }
}
