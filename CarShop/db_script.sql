/*=====================================================*/
/*                      DB SCRIPT                      */
/*=====================================================*/

CREATE DATABASE IF NOT EXISTS carshop_db;

USE carshop_db;

DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS order_products_list;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS producers;
DROP TABLE IF EXISTS categories;

CREATE TABLE users (
	id BIGINT primary key auto_increment NOT NULL,
    username VARCHAR(255) unique NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    news boolean,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    ban boolean
);

CREATE TABLE user_roles (
	user_id BIGINT NOT NULL,
	role_name VARCHAR(255) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE cascade
);
   
CREATE TABLE producers (
	id BIGINT primary key auto_increment NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
	id BIGINT primary key auto_increment NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
	id BIGINT primary key auto_increment NOT NULL,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
	category_id BIGINT NOT NULL,
    producer_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE cascade,
    FOREIGN KEY (producer_id) REFERENCES producers(id) ON DELETE cascade
);

CREATE TABLE orders (
	id BIGINT primary key auto_increment NOT NULL,
    status VARCHAR(255),
    message VARCHAR(255),
    date datetime,
    user_id BIGINT NOT NULL,
    pay_type VARCHAR(255),
    foreign key(user_id) REFERENCES users(id) ON DELETE cascade
);

CREATE TABLE order_products_list (
	id_order BIGINT NOT NULL,
    id_product BIGINT NOT NULL,
    numbers INT,
    price FLOAT,
    FOREIGN KEY (id_order) REFERENCES orders(id) ON DELETE cascade,
    FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE cascade
);


/*=====================================================*/
/*                      TEST DATA                      */
/*=====================================================*/
INSERT INTO users values(1,'admin','admin','admin@gmail.com',true, 'Admin', 'Admin', false);

INSERT INTO producers values(1, 'Ford');
INSERT INTO producers values(2, 'Lexus');
INSERT INTO producers values(3, 'Porsche');

INSERT INTO categories values(1, 'Sport');
INSERT INTO categories values(2, 'Lux');

INSERT INTO products values(1, 'Ford Mustang', 13000, 2, 1);
INSERT INTO products values(2, 'Lexus RX-350', 17000, 2, 2);
INSERT INTO products values(3, 'Porsche Cayenne', 11000, 2, 3);
INSERT INTO products values(4, 'Porsche 718 Cayman',15000, 2, 3);
INSERT INTO products values(5, 'Porsche Boxster',  18000, 2, 3);
INSERT INTO products values(6, 'Porsche Carrera S',  11000, 2, 3);
INSERT INTO products values(7, 'Porsche Taycan',  21000, 2, 3);
INSERT INTO products values(8, 'Porsche Panamera',  9000, 2, 3);
INSERT INTO products values(9, 'Porsche Panamera GTS', 14500, 2, 3);
INSERT INTO products values(10, 'Porsche E-Hybrid', 19000, 2, 3);
INSERT INTO products values(11, 'Porsche  Turbo', 23000, 2, 3);

INSERT INTO user_roles VALUES(1, "user");
INSERT INTO user_roles VALUES(1, "admin");

Select role_name from users, user_roles
where users.id = user_roles.user_id 
	and username = "admin";
    
SELECT * FROM orders;
SELECT * FROM users;




