package com.kozhukhar.carshop_online.util;

import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.web.resource_tag.FieldTags;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    public void initUserField(ResultSet rs, User newUser) throws SQLException {
        newUser.setId(rs.getInt(FieldTags.ID));
        newUser.setUsername(rs.getString(FieldTags.USERNAME));
        newUser.setPassword(rs.getString(FieldTags.PASSWORD));
        newUser.setEmail(rs.getString(FieldTags.EMAIL));
        newUser.setName(rs.getString(FieldTags.NAME));
        newUser.setSurname(rs.getString(FieldTags.SURNAME));
        newUser.setNews(rs.getBoolean(FieldTags.NEWS));
        newUser.setBan(rs.getBoolean(FieldTags.BAN));
    }

    public void initProductField(ResultSet rs, Product newProduct) throws SQLException {
        newProduct.setId(rs.getInt(FieldTags.ID));
        newProduct.setName(rs.getString(FieldTags.NAME));
        newProduct.setPrice(rs.getFloat(FieldTags.PRICE));
        newProduct.setCategory(rs.getString(FieldTags.CATEGORY_NAME));
        newProduct.setProducer(rs.getString(FieldTags.PRODUCER_NAME));
    }

}
