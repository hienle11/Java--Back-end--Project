package service;

import dao.GenericDAO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("orderService")
public class OrderServiceImpl extends AbstractCRUDService<Order, Long>{

    @Autowired
    @Qualifier("orderDAOImpl")
    GenericDAO orderDAO;

    @Override
    protected GenericDAO getDao() {
        return orderDAO;
    }
}
