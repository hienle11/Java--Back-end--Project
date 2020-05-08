package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController extends AbstractCRUDController<Order, Long>{

    @Autowired
    @Qualifier("orderServiceImpl")
    GenericService orderService;

    @Override
    protected GenericService getService() {
        return orderService;
    }
}