package service;

import dao.OrderDAO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractCRUDService<Order, Long> implements GenericService<Order, Long>{

    @Autowired
    OrderService(OrderDAO dao) {
        super(dao);
    }
}
