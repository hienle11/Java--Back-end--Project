package service;

import dao.GenericDAO;
import dao.ReceivingNoteDAO;
import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingNoteServiceImpl extends AbstractService<ReceivingNote, Long>
        implements GenericService<ReceivingNote, Long>, ReceivingNoteService {

    @Autowired
    @Qualifier("receivingNoteDAOImpl")
    GenericDAO receivingNoteDAO;

    @Autowired
    ReceivingNoteDAO customReceivingNoteDAO;

    @Override
    protected GenericDAO getDao() {
        return receivingNoteDAO;
    }

    @Override
    public Page<ReceivingNote> searchByPeriod(String startDate, String endDate, Pageable pageable) {
        List<ReceivingNote> wholeResultList = customReceivingNoteDAO.searchByPeriod(startDate, endDate);
        return getPage(wholeResultList, pageable);
    }

    @Override
    public List<ReceivingNote> searchByPeriod(String startDate, String endDate) {
        return customReceivingNoteDAO.searchByPeriod(startDate, endDate);
    }
}
