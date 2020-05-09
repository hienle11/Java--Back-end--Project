package service;

import dao.GenericDAO;
import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeliveryNoteServiceImpl extends AbstractCRUDService<DeliveryNote, Long> implements GenericService<DeliveryNote, Long> {
    @Autowired
    @Qualifier("deliveryNoteDAOImpl")
    GenericDAO deliveryNoteDAO;

    @Override
    protected GenericDAO getDao() {
        return deliveryNoteDAO;
    }
}
