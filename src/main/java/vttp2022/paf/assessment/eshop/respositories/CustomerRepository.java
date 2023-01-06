package vttp2022.paf.assessment.eshop.respositories;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		// TODO: Task 3 
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_AUTHENTICATE_CUSTOMER, name);
		final Optional<Customer> customer = findCustomerByName(name);

		while (rs.next())
			if (customer.isPresent()) {
				// value is present inside Optional
				System.out.println("Customer exists");
			} else {
				// value is absent
				String message = " 404 Error! User does not exist";
			}

		return customer;
		

	}
}
