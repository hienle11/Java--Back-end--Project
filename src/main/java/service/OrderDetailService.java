package service;

import dao.OrderDetailDAO;
import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService extends AbstractCRUDService<OrderDetail, Long> implements GenericService<OrderDetail, Long>{

    @Autowired
    OrderDetailService(OrderDetailDAO dao) {
        super(dao);
    }
}
