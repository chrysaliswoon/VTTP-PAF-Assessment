package vttp2022.paf.assessment.eshop.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder (Order o) throws OrderException {

        //? Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0,8);

        //? Set the orderId
        o.setOrderId(orderId);

        //? Generate Order Date
        // Instantiate a Date object
        Date date = new Date();

        // display time and date
        // String str = String.format("Current Date/Time : %tc", date );

        //? Set the order date
        o.setOrderDate(date);
        

        //? Create purchase order
        orderRepo.insertPurchaseOrder(o);
        throw new OrderException("Exception for orderId %s".formatted(orderId));
    }
    
}
