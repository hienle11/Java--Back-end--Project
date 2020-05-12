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

                // map price for order detail from quantity and product price
                Product incomingProduct = orderDetail.getProduct();
                Long productId = incomingProduct.getId();
                Product product = sessionFactory.getCurrentSession().get(Product.class, productId);
                if (product != null) {
                    orderDetail.setPrice(product.getPrice() * orderDetail.getQuantity());
                }
            }
        } else {
            order.setOrderDetails(null);
        }
    }

    @Override
    public List<Order> searchPaginated(String field, String searchKey, int limit, int offset) {
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
            int count = 0;
            for(Long eachId: orderIds) {
                count++;
                if (count > offset) {
                    result.add(findById(eachId));
                    limit--;
                }
                if (limit == 0) {
                    break;
                }
            }
            return result;
        } else {
            return super.searchPaginated(field, searchKey, limit, offset);
        }
    }
}
