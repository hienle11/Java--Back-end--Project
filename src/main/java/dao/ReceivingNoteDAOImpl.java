package dao;

import entity.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
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

    @Override
    public ReceivingNote update(ReceivingNote receivingNote) {
        receivingNote.setOrder(null);
        receivingNote.setReceivingNoteDetails(null);
        return super.update(receivingNote);
    }

    @Override
    public List<ReceivingNote> searchPaginated(String field, String searchKey, int limit, int offset) {
        if (field.equalsIgnoreCase("product")) {
            Query<ReceivingNoteDetail> query = sessionFactory.getCurrentSession()
                    .createQuery("from ReceivingNoteDetail "
                            + " where str(" + field + ") like '%" + searchKey + "%'");
            List<ReceivingNoteDetail> noteDetailResults = query.getResultList();
            Set<Long> receivingNoteIds = new HashSet<>();
            for(ReceivingNoteDetail receivingNoteDetail: noteDetailResults) {
                receivingNoteIds.add(receivingNoteDetail.getReceivingNote().getId());
            }
            List<ReceivingNote> result = new ArrayList<>();
            int count = 0;
            for(Long eachId: receivingNoteIds) {
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
