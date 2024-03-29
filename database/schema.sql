DROP SCHEMA IF EXISTS `eshop` ;
CREATE SCHEMA IF NOT EXISTS `eshop` DEFAULT CHARACTER SET latin1 ;
USE eshop;

-- | Field name | Field type | Field Description |
-- |------------|------------|-------------------|
-- |Name        |   String   |Unique identifier of a customer and max. of 32 characters|
-- |Address     |   String   |Can store addresses up to a max. of 128 characters. All customers must have an address|
-- |Email       |   String   |Emails are mandatory and max. of 128 characters.|


-- -----------------------------------------------------
-- Table `eshop`.`customers`
-- -----------------------------------------------------
CREATE TABLE customers (
    `name` VARCHAR(32) NOT NULL, 
    `address` VARCHAR(128) NOT NULL,
    `email` VARCHAR(128) NOT NULL ,

    PRIMARY KEY (name)
);

-- Insert csv data into the schema
LOAD DATA LOCAL INFILE 'data.csv' 
INTO TABLE customers 
FIELDS TERMINATED BY ':' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;


-- | Field name | Field type |
-- |------------|------------|
-- |order_id|integer|
-- |delivery_id|auto-increment|
-- |customer_name|varchar(128)|
-- |ship_address|varchar(128)|
-- |email|varchar(128)|
-- |status|varchar(128)|
-- |created_date|date| 

-- ```Orders``` table has a one to many relationships with ```Order_details```.

CREATE TABLE orders (
    order_id int NOT NULL UNIQUE,
    delivery_id int AUTO_INCREMENT NOT NULL UNIQUE,
    customer_name varchar(128) NOT NULL,
    ship_address varchar(128) NOT NULL,
    email varchar(128) NOT NULL,
    status varchar(128),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    PRIMARY KEY (order_id),
        constraint fk_customer_name
            FOREIGN KEY (customer_name)
            references customers(name)
);

-- | Field name | Field type |
-- |------------|------------|
-- |id|auto_increment|
-- |item|varchar(64)|
-- |quantity|int|

-- ```Orders``` table has a one to many relationships with ```Order_details```.

CREATE TABLE orderDetails (
    orderDetails_id int AUTO_INCREMENT NOT NULL UNIQUE,
    item varchar(64) NOT NULL,
    quantity int,
    order_id int NOT NULL,

    PRIMARY KEY (orderDetails_id),
    constraint fk_order_id
        FOREIGN KEY (order_id)
        references orders(order_id)
);


-- | Field name | Field type | Field Description |
-- |------------|------------|-------------------|
-- |order_id        |  Integer    |The order id of the dispatched order
-- |delivery_id     |   String   |The delivery id is returned by the dispatch endpoint. Delivery id is not longer than 128 characters
-- |status          |    Enum  |Delivery status. This column can contain either pending or dispatched value
-- |status_update         |   Date   |A timestamp when the orders status is inserted into the order_status table


-- -----------------------------------------------------
-- Table `eshop`.`order_status`
-- -----------------------------------------------------

CREATE TABLE order_status (
    `order_id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `delivery_id` VARCHAR(128) NOT NULL,
    `status` ENUM('pending', 'dispatched'),
    `status_update` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    PRIMARY KEY (order_id)
);