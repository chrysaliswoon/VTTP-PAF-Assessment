package vttp2022.paf.assessment.eshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

@Service
public class WarehouseService {

	

	
	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {
		// TODO: Task 4

		//? Get order id
		String orderId = order.getOrderId();

		//? Create the REST endpoint
		final String DISPATCH_URL = "http://paf.chuklee.com/dispatch/" + orderId;

		//? Insert order status to database




		//? Send order status back to client


		return null;
		


	}
}
