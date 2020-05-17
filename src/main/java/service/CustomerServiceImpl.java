package service;

import dao.GenericDAO;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends AbstractService<Customer, Long> {

    @Autowired
    @Qualifier("customerDAOImpl")
    private GenericDAO customerDAO;

    @Override
    protected GenericDAO getDao() {
        return customerDAO;
    }
}
