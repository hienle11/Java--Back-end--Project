package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping("/api")
public class SpecialAPIController {

    @Autowired
    @Qualifier("salesInvoiceServiceImpl")
    GenericService salesInvoiceService;

}
