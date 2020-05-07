package service;


import dao.ReceivingNoteDetailDAO;
import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceivingNoteDetailService extends AbstractCRUDService<ReceivingNoteDetail, Long> implements GenericService<ReceivingNoteDetail, Long>{

    @Autowired
    ReceivingNoteDetailService(ReceivingNoteDetailDAO dao) {
        super(dao);
    }
}
