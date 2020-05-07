package controller;

import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CustomerService;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController extends AbstractCRUDController<Customer, Long>{

    @Autowired
    CustomerController(CustomerService service) {
        super(service);
    }
}
