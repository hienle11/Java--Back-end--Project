package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController extends AbstractCRUDController<Order, Long>{

    @Autowired
    OrderController(OrderService service) {
        super(service);
    }
}