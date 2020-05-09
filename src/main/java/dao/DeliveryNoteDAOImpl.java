package dao;

import entity.DeliveryNote;
import entity.DeliveryNoteDetail;
import entity.Product;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryNoteDAOImpl extends AbstractHibernateDAO<DeliveryNote, Long> {
    @Override
    public DeliveryNote create(DeliveryNote deliveryNote) {
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
        return super.create(deliveryNote);
    }

    @Override
    public DeliveryNote update(DeliveryNote deliveryNote) {
        deliveryNote.setDeliveryNoteDetails(null);
        return super.update(deliveryNote);
    }

}