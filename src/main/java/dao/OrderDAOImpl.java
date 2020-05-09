package dao;

import entity.*;
import entity.Order;
import entity.OrderDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl extends AbstractHibernateDAO<Order, Long> {

    @Override
    public Order create(Order order) {
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
        return super.create(order);
    }

    @Override
    public Order update(Order order) {
        order.setOrderDetails(null);
        return super.update(order);
    }
}
