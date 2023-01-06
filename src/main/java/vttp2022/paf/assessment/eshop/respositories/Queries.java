package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    //? Authenticate and check if the customer exists in the table
    public static final String SQL_AUTHENTICATE_CUSTOMER =  "select count (*) > 0 as auth_state from customers where name = ?";

    public static final String SQL_AUTHENTICATE_CUSTOMER_DETAILS = "select * from customers where name = ?";
    
}
