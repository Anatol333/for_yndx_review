package com.kozhukhar.carshop_online.db;

public class Queries {

    public static final String SQL_GET_USER_BY_USERNAME = "select * from users where username = ?";
    public static final String SQL_SAVE_NEW_USER = "insert into users(username, password, email, name, surname, news) VALUES(?,?,?,?,?,?)";
    public static final String SQL_GET_USER_BY_EMAIL = "select * from users where email = ?";
    public static final String SQL_GET_PRODUCTS_BY_PAGE_NUM = "SELECT P.id, P.name, P.price, C.name as 'category_name', PR.name as 'producer_name'  \n" +
            "FROM products P, producers PR, categories C\n" +
            "WHERE PR.id = P.producer_id\n" +
            "\tAND C.id = P.category_id \n" +
            "ORDER BY id DESC\n" +
            "limit ?, ?";
    public static final String SQL_GET_ALL_PRODUCTS = "SELECT name, price FROM products;";
    public static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM categories";
    public static final String SQL_GET_ALL_PRODUCERS = "SELECT * FROM producers";
    public static final String SQL_GET_PRODUCTS_BY_ID = "SELECT P.id, P.name, P.price, C.name as 'category_name', PR.name as 'producer_name'" +
            "FROM products P, producers PR, categories C\n" +
            "WHERE PR.id = P.producer_id\n" +
            "\tAND C.id = P.category_id \n" +
            " AND P.id = ?";
    public static final String SQL_SAVE_NEW_ORDER = "\n" +
            "INSERT INTO orders(status, message, date, user_id, pay_type) VALUES(?,?,?,?,?);\n";
    public static final String SQL_GET_ALL_ORDERS = "select * from orders";
    public static final String SQL_SAVE_ORDER_PRODUCTS = "INSERT INTO order_products_list VALUES(?,?,?,?);";
    public static final String SQL_ADD_ROLE_TO_USER = "INSERT INTO user_roles VALUES(?, ?);";
    public static final String SQL_GET_USER_ROLES_BY_USERNAME = "Select role_name from users, user_roles\n" +
            "where users.id = user_roles.user_id " +
            " and username = ? ";
    public static final String SQL_UPDATE_USER_BAN = "UPDATE users SET ban = ? WHERE id = ?";
}
