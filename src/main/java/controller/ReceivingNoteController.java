package controller;

import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;
import service.ReceivingNoteService;

@RestController
@RequestMapping(path = "/receiving-notes")
public class ReceivingNoteController extends AbstractCRUDController<ReceivingNote, Long>{

    @Autowired
    @Qualifier("receivingNoteService")
    GenericService receivingNoteService;

    @Override
    protected GenericService getService() {
        return receivingNoteService;
    }
}
