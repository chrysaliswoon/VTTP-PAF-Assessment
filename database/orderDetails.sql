USE eshop;

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