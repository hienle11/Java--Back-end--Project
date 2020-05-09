package dao;

import entity.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("receivingNoteDAO")
public class ReceivingNoteDAOImpl extends AbstractHibernateDAO<ReceivingNote, Long> {

    @Override
    public ReceivingNote create(ReceivingNote receivingNote) {

        // get products and corresponding quantities in the order
        Long orderId = receivingNote.getOrder();
        if (orderId > 0)
        {
            List<OrderDetail> orderDetails =
                    sessionFactory.getCurrentSession().get(Order.class, orderId).getOrderDetails();
            List<ReceivingNoteDetail> receivingNoteDetails = new ArrayList<>();

            for (OrderDetail orderDetail: orderDetails) {
                ReceivingNoteDetail newNote = new ReceivingNoteDetail();
                newNote.setReceivingNote(receivingNote);           // set receiving note for each note detail
                newNote.setProduct(orderDetail.getProduct());           // transfer product to note detail
                newNote.setQuantity(orderDetail.getQuantity());         // transfer quantity to note detail
                receivingNoteDetails.add(newNote);
            }
            receivingNote.setReceivingNoteDetails(receivingNoteDetails);
        }

        return super.create(receivingNote);
    }
}
