package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    //? Authenticate and check if the customer exists in the table
    public static final String SQL_AUTHENTICATE_CUSTOMER =  "select count (*) > 0 as auth_state from customers where name = ?";
    public static final String SQL_AUTHENTICATE_CUSTOMER_DETAILS = "select * from customers where name = ?";

    //? Create a new purchase order
    public static String SQL_INSERT_PURCHASE_ORDER = "INSERT INTO orders(order_id, delivery_id, customer_name, created_date) VALUES (?, ?, ?, ?)";
    public static String SQL_INSERT_LINE_ITEM = "INSERT INTO orderDetails (orderDetails_id, item, quantity, order_id) VALUES (?, ?, ?, ?)";
    
}
