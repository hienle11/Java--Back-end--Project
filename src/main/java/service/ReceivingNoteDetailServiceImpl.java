package service;


import dao.GenericDAO;
import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("receivingNoteDetailService")
public class ReceivingNoteDetailServiceImpl extends AbstractCRUDService<ReceivingNoteDetail, Long> implements GenericService<ReceivingNoteDetail, Long> {

    @Autowired
    @Qualifier("receivingNoteDetailDAOImpl")
    GenericDAO receivingNoteDetailDAO;

    @Override
    protected GenericDAO getDao() {
        return receivingNoteDetailDAO;
    }
}
