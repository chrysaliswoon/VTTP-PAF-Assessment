package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
// @RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRESTController {

    
    // @Autowired
    // private OrderRepository orderRepo;

    // @Autowired
    // private CustomerRepository customerRepo;
    
    // //? Get status of order by name
    // // GET /api/order/<name>/status
    // // Accept: application/json
    // @GetMapping(path = "/{name}/status")
    // public ResponseEntity<String> getOrderStatus(@PathVariable String name) {
    //     Optional<List<Order>> order = orderRepo.getOrderStatus(name);
    //     Optional<Customer> customer = customerRepo.findCustomerByName(name);

    //     if (customer.isEmpty()) {
    //         String message = "Customer " + name + " not found" ;
    //         return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    //     } else {
    //         if (order.isEmpty()) {
    //             String message = " 404 Error! Order details for this customer does not exist!";
    //             return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    //         } else {
    //             JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    //             for (Order o: order.get())
    //                 arrayBuilder.add(o.toJson());
    //             JsonArray result = arrayBuilder.build();
    
    //             return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(result.toString());
    //         }
    //     }

    // }

}
