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
public class DeliveryNoteDAOImpl extends AbstractHibernateDAO<DeliveryNote, Long> implements DeliveryNoteDAO {
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
    public List<DeliveryNote> searchPaginated(String field, String searchKey) {
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
            for (Long eachId : deliveryNoteIds) {
                result.add(findById(eachId));
            }
            return result;
        } else {
            return super.searchPaginated(field, searchKey);
        }
    }

    @Override
    public List<DeliveryNote> searchByPeriod(String startDate, String endDate) {
        Query<DeliveryNote> query = sessionFactory.getCurrentSession()
                .createQuery("from DeliveryNote"
                        + " where (date >= '" + startDate + "' AND date <= '" + endDate + "')");
        return query.list();
    }
}