package service;

import dao.CustomerDAO;
import dao.GenericDAO;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("customerService")
public class CustomerService extends AbstractCRUDService<Customer, Long>{

    @Autowired
    @Qualifier("customerDAO")
    GenericDAO customerDAO;

    @Override
    protected GenericDAO getDao() {
        return customerDAO;
    }
}
