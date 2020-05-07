package service;

import dao.ReceivingNoteDAO;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceivingNoteService extends AbstractCRUDService<ReceivingNote, Long> implements GenericService<ReceivingNote, Long>{

    @Autowired
    ReceivingNoteService(ReceivingNoteDAO dao) {
        super(dao);
    }
}
