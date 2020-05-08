package controller;

import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController extends AbstractCRUDController<Customer, Long>{

    @Autowired
    @Qualifier("customerServiceImpl")
    GenericService customerService;

    @Override
    protected GenericService getService() {
        return customerService;
    }
}
