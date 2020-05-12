package controller;

import dto.RevenueReport;
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
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String searchKey,
            @RequestParam(required = false) Optional<Long> size,
            @RequestParam(required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));
        Page<SalesInvoice> resultPage;
        if (field != null && searchKey != null) {
            resultPage = customSalesInvoiceService.searchByFieldAndPeriod(
                    startDate, endDate,  field, searchKey,
                    PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));
        } else {
            resultPage = customSalesInvoiceService.searchByPeriod(
                    startDate, endDate, PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));
        }

        return resultPage;
    }

    @GetMapping("/revenue")
    public RevenueReport getTotalRevenue(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam(name = "field") String field,
            @RequestParam(name = "searchKey") String searchKey) {

        return customSalesInvoiceService.getTotalRevenue(startDate, endDate, field, searchKey);
    }
}