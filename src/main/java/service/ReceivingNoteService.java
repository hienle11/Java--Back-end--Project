package service;

import dao.GenericDAO;
import dao.ReceivingNoteDAO;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("receivingNoteService")
public class ReceivingNoteService extends AbstractCRUDService<ReceivingNote, Long> implements GenericService<ReceivingNote, Long>{

    @Autowired
    @Qualifier("receivingNoteDAO")
    GenericDAO receivingNoteDAO;

    @Override
    protected GenericDAO getDao() {
        return receivingNoteDAO;
    }
}
