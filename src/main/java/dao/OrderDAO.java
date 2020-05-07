package dao;

import entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO
        extends AbstractHibernateDAO<Order, Long>
        implements GenericDAO<Order, Long> {
}
