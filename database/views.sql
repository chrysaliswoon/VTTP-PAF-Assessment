create view status as
	select orders.order_id, orders.customer_name, order_status.status
    from orders 
        join order_status 
        on orders.order_id=order_status.order_id;

