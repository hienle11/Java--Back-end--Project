package controller;

import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OrderDetailService;

@RestController
@RequestMapping(path = "/order-details")
public class OrderDetailController extends AbstractCRUDController<OrderDetail, Long>{

    @Autowired
    OrderDetailController(OrderDetailService service) {
        super(service);
    }
}