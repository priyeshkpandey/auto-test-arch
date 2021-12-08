DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(32) NOT NULL,
  role_name VARCHAR(32) NOT NULL,
  permission_name VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS permission;

CREATE TABLE permission (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   permission_name VARCHAR(32) NOT NULL,
   type VARCHAR(32) NOT NULL,
   resource_type VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS role;

CREATE TABLE role (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   role_name VARCHAR(32) NOT NULL,
   permission_name VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   product_name VARCHAR(32) NOT NULL,
   product_description VARCHAR(250) NOT NULL,
   price FLOAT NOT NULL,
   discount FLOAT NOT NULL
);

DROP TABLE IF EXISTS order;

CREATE TABLE order (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   user_id INT,
   payment_status INT,
   products_json VARCHAR(1000),
   total_price FLOAT
);