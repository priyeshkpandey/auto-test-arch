DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(32) NOT NULL,
  role_name VARCHAR(32) NOT NULL,
  permission_name VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS permissions;

CREATE TABLE permissions (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   permission_name VARCHAR(32) NOT NULL,
   type VARCHAR(32) NOT NULL,
   resource_type VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   role_name VARCHAR(32) NOT NULL,
   permission_name VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS products;

CREATE TABLE products (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   product_name VARCHAR(32) NOT NULL,
   product_description VARCHAR(250) NOT NULL,
   price FLOAT NOT NULL,
   discount FLOAT NOT NULL
);