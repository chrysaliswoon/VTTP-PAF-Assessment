USE eshop;

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