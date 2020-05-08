package dao;

import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("orderDetailDAO")
public class OrderDetailDAOImpl extends AbstractHibernateDAO<OrderDetail, Long> {
}