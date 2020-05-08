package controller;

import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/receiving-note-details")
public class ReceivingNoteDetailController extends AbstractCRUDController<ReceivingNoteDetail, Long> {

    @Autowired
    @Qualifier("receivingNoteDetailServiceImpl")
    GenericService receivingNoteDetailService;

    @Override
    protected GenericService getService() {
        return receivingNoteDetailService;
    }
}
