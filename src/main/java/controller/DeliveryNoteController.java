package controller;

import entity.DeliveryNote;
import entity.ReceivingNote;
import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DeliveryNoteService;
import service.GenericService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/delivery-notes")
public class DeliveryNoteController extends AbstractCRUDController<DeliveryNote, Long>{

    @Autowired
    @Qualifier("deliveryNoteServiceImpl")
    private GenericService deliveryNoteService;

    @Autowired
    private DeliveryNoteService customDeliveryNoteService;

    @Override
    protected GenericService getService() {
        return deliveryNoteService;
    }

    @GetMapping("/search-by-period")
    public Page<DeliveryNote> searchByPeriod(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam(name = "size", required = false) Optional<Long> size,
            @RequestParam(name = "page", required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));

        Page<DeliveryNote> resultPage = customDeliveryNoteService.searchByPeriod(
                startDate, endDate, PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));

        return resultPage;
    }
}
