package service;

import dao.GenericDAO;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ReceivingNoteServiceImpl extends AbstractService<ReceivingNote, Long> implements GenericService<ReceivingNote, Long> {

    @Autowired
    @Qualifier("receivingNoteDAOImpl")
    GenericDAO receivingNoteDAO;

    @Override
    protected GenericDAO getDao() {
        return receivingNoteDAO;
    }
}
