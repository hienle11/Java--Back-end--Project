package controller;

import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/sales-invoices")
public class SalesInvoiceController extends AbstractCRUDController<SalesInvoice, Long>{

    @Autowired
    @Qualifier("salesInvoiceServiceImpl")
    GenericService salesInvoiceService;

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
}