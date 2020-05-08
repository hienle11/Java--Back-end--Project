package service;

import dao.GenericDAO;
import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("orderDetailService")
public class OrderDetailServiceImpl extends AbstractCRUDService<OrderDetail, Long> implements GenericService<OrderDetail, Long> {

    @Autowired
    @Qualifier("orderDetailDAOImpl")
    GenericDAO orderDetailDAO;

    @Override
    protected GenericDAO getDao() {
        return orderDetailDAO;
    }
}
