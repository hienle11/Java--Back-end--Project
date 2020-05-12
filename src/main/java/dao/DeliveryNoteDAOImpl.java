package dao;

import entity.DeliveryNote;
import entity.DeliveryNoteDetail;
import entity.Product;
import entity.ReceivingNote;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DeliveryNoteDAOImpl extends AbstractHibernateDAO<DeliveryNote, Long> {
    @Override
    public DeliveryNote create(DeliveryNote deliveryNote) {
        preProcess(deliveryNote);
        return super.create(deliveryNote);
    }

    @Override
    public DeliveryNote update(DeliveryNote deliveryNote) {
        preProcess(deliveryNote);
        return super.update(deliveryNote);
    }

    private void preProcess(DeliveryNote deliveryNote) {
        List<DeliveryNoteDetail> deliveryNoteDetails = deliveryNote.getDeliveryNoteDetails();
        if (deliveryNoteDetails != null && deliveryNoteDetails.size() > 0)
        {
            for (DeliveryNoteDetail deliveryNoteDetail : deliveryNoteDetails) {
                // set oder for deliveryNote detail
                deliveryNoteDetail.setDeliveryNote(deliveryNote);
            }
        } else {
            deliveryNote.setDeliveryNoteDetails(null);
        }
    }

    @Override
    public List<DeliveryNote> searchPaginated(String field, String searchKey, int limit, int offset) {
        if (field.equalsIgnoreCase("product")) {
            Query<DeliveryNoteDetail> query = sessionFactory.getCurrentSession()
                    .createQuery("from DeliveryNoteDetail "
                            + " where str(" + field + ") like '%" + searchKey + "%'");
            List<DeliveryNoteDetail> noteDetailResults = query.list();
            Set<Long> deliveryNoteIds = new HashSet<>();
            for (DeliveryNoteDetail deliveryNoteDetail : noteDetailResults) {
                deliveryNoteIds.add(deliveryNoteDetail.getDeliveryNote().getId());
            }
            List<DeliveryNote> result = new ArrayList<>();
            int count = 0;
            for (Long eachId : deliveryNoteIds) {
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