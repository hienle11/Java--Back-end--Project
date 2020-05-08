package controller;

import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/order-details")
public class OrderDetailController extends AbstractCRUDController<OrderDetail, Long>{

    @Autowired
    @Qualifier("orderDetailServiceImpl")
    GenericService orderDetailService;

    @Override
    protected GenericService getService() {
        return orderDetailService;
    }
}