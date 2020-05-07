package service;

import dao.CustomerDAO;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractCRUDService<Customer, Long> implements GenericService<Customer, Long>{

    @Autowired
    CustomerService(CustomerDAO dao) {
        super(dao);
    }
}
