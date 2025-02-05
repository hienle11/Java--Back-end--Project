package dao;

import entity.*;
import entity.Order;
import entity.OrderDetail;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDAOImpl extends AbstractHibernateDAO<Order, Long> {

    @Override
    public Order create(Order order) {
        preProcess(order);
        return super.create(order);
    }

    @Override
    public Order update(Order order) {
        preProcess(order);
        return super.update(order);
    }

    private void preProcess(Order order) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
        if (orderDetails != null && orderDetails.size() > 0)
        {
            for (OrderDetail orderDetail : orderDetails) {

                // set oder for order detail
                orderDetail.setOrder(order);
            }
        } else {
            order.setOrderDetails(null);
        }
    }

    @Override
    public List<Order> searchPaginated(String field, String searchKey) {
        if (field.equalsIgnoreCase("product")) {
            Query<OrderDetail> query = sessionFactory.getCurrentSession()
                    .createQuery("from OrderDetail "
                            + " where str(" + field + ") like '%" + searchKey + "%'");
            List<OrderDetail> invoiceDetailResults = query.getResultList();
            Set<Long> orderIds = new HashSet<>();
            for(OrderDetail orderDetail: invoiceDetailResults) {
                orderIds.add(orderDetail.getOrder().getId());
            }
            List<Order> result = new ArrayList<>();
            for(Long eachId: orderIds) {
                    result.add(findById(eachId));
            }
            return result;
        } else {
            return super.searchPaginated(field, searchKey);
        }
    }
}
