package controller;

import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
}