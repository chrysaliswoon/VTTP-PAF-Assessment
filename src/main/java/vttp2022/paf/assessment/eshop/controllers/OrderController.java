package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@Controller
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	// TODO: Task 3

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private WarehouseService warehouseSvc;

	@PostMapping
	public ResponseEntity<String> postOrder(@RequestBody MultiValueMap<String, String> form, Model model) {

		String name = form.getFirst("name");
		// String name = form.get(model);

		Optional<Customer> customer = customerRepo.findCustomerByName(name);
		if (customer.isEmpty()) {
			String message = "Customer " + name + " not found";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} else {
			Order o = new Order();

			// ? Generate orderId
			String orderId = UUID.randomUUID().toString().substring(0, 8);

			// ? Set the orderId
			o.setOrderId(orderId);

			// ? Insert purchase order into database
			orderRepo.insertPurchaseOrder(o);

			//? Dispatch order to warehouse
			warehouseSvc.dispatch(o);

			return null;
		}

	}

	// ? Get status of order by name
	// GET /api/order/<name>/status
	// Accept: application/json
	@GetMapping(path = "/{name}/status")
	public ResponseEntity<String> getOrderStatus(@PathVariable String name, Model model) {
		Optional<List<Order>> order = orderRepo.getOrderStatus(name);

		if (order.isEmpty()) {
			String message = " 404 Error! Order details for this customer does not exist!";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} else {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (Order o : order.get())
				arrayBuilder.add(o.toJson());
			JsonArray result = arrayBuilder.build();

			return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body(result.toString());
		}
	}

	// //? Add items to cart
	// @PostMapping("/")
	// public String postOrder(@RequestBody MultiValueMap<String, String> form) {

	// String name = form.getFirst("name");

	// System.out.print(name);
	// return "status";
	// }
	// }
}
