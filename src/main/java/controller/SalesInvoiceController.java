package controller;

import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import service.GenericService;
import service.SalesInvoiceService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/sales-invoices")
public class SalesInvoiceController extends AbstractCRUDController<SalesInvoice, Long>{

    @Autowired
    @Qualifier("salesInvoiceServiceImpl")
    GenericService salesInvoiceService;

    @Autowired
    SalesInvoiceService customSalesInvoiceService;

    @Override
    protected GenericService getService() {
        return salesInvoiceService;
    }

    @Override
    @PutMapping
    public SalesInvoice updateEntity(@RequestBody SalesInvoice salesInvoice) {
        Long salesInvoiceId = salesInvoice.getId();
        super.updateEntity(salesInvoice);
        // update the SalesInvoice again to calculate the total amount correctly
        return (SalesInvoice) salesInvoiceService.update((SalesInvoice) salesInvoiceService.findById(salesInvoiceId));
    }

    @GetMapping("/search-by-period")
    public Page<SalesInvoice> searchByPeriod(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam(name = "size", required = false) Optional<Long> size,
            @RequestParam(name = "page", required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));

        Page<SalesInvoice> resultPage = customSalesInvoiceService.searchByPeriod(
                startDate, endDate, PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));

        return resultPage;
    }
}