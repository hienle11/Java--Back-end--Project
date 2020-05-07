package dao;

import entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailDAO
        extends AbstractHibernateDAO<OrderDetail, Long>
        implements GenericDAO<OrderDetail, Long> {
}