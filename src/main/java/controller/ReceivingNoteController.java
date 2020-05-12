package controller;

import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;
import service.ReceivingNoteService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/receiving-notes")
public class ReceivingNoteController extends AbstractCRUDController<ReceivingNote, Long>{

    @Autowired
    @Qualifier("receivingNoteServiceImpl")
    GenericService receivingNoteService;

    @Autowired
    ReceivingNoteService customReceivingNoteService;

    @Override
    protected GenericService getService() {
        return receivingNoteService;
    }

    @GetMapping("/search-by-period")
    public Page<ReceivingNote> searchByPeriod(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam(name = "size", required = false) Optional<Long> size,
            @RequestParam(name = "page", required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));

        Page<ReceivingNote> resultPage = customReceivingNoteService.searchByPeriod(
                startDate, endDate, PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));

        return resultPage;
    }
}
