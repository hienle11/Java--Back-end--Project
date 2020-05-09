package controller;

import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/delivery-notes")
public class DeliveryNoteController extends AbstractCRUDController<DeliveryNote, Long>{

    @Autowired
    @Qualifier("deliveryNoteServiceImpl")
    GenericService deliveryNoteService;

    @Override
    protected GenericService getService() {
        return deliveryNoteService;
    }
}
