package service;

import dao.DeliveryNoteDAO;
import dao.GenericDAO;
import entity.DeliveryNote;
import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryNoteServiceImpl extends AbstractService<DeliveryNote, Long>
        implements GenericService<DeliveryNote, Long>, DeliveryNoteService {
    @Autowired
    @Qualifier("deliveryNoteDAOImpl")
    GenericDAO deliveryNoteDAO;

    @Autowired
    DeliveryNoteDAO customDeliveryNoteDAO;

    @Override
    protected GenericDAO getDao() {
        return deliveryNoteDAO;
    }

    @Override
    public Page<DeliveryNote> searchByPeriod(String startDate, String endDate, Pageable pageable) {
        List<DeliveryNote> wholeResultList = customDeliveryNoteDAO.searchByPeriod(startDate, endDate);
        return getPage(wholeResultList, pageable);
    }
}
