package service;

import dao.GenericDAO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends AbstractService<Order, Long> {

    @Autowired
    @Qualifier("orderDAOImpl")
    private GenericDAO orderDAO;

    @Override
    protected GenericDAO getDao() {
        return orderDAO;
    }
}
