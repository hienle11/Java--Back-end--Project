package service;


import dao.GenericDAO;
import dao.ReceivingNoteDetailDAO;
import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("receivingNoteDetailService")
public class ReceivingNoteDetailService extends AbstractCRUDService<ReceivingNoteDetail, Long> implements GenericService<ReceivingNoteDetail, Long>{

    @Autowired
    @Qualifier("receivingNoteDetailDAO")
    GenericDAO receivingNoteDetailDAO;

    @Override
    protected GenericDAO getDao() {
        return receivingNoteDetailDAO;
    }
}
