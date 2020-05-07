package controller;

import controller.AbstractCRUDController;
import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ReceivingNoteDetailService;

@RestController
@RequestMapping(path = "/receiving-note-details")
public class ReceivingNoteDetailController extends AbstractCRUDController<ReceivingNoteDetail, Long> {

    @Autowired
    ReceivingNoteDetailController(ReceivingNoteDetailService service) {
        super(service);
    }
}
