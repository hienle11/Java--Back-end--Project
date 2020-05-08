package service;

import dao.GenericDAO;
import dao.OrderDAO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("orderService")
public class OrderService extends AbstractCRUDService<Order, Long>{

    @Autowired
    @Qualifier("orderDAO")
    GenericDAO orderDAO;

    @Override
    protected GenericDAO getDao() {
        return orderDAO;
    }
}
