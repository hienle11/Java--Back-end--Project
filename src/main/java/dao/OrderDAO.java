package dao;

import entity.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("orderDAO")
public class OrderDAO extends AbstractHibernateDAO<Order, Long> {
}
