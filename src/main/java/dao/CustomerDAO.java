package dao;

import entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO
        extends AbstractHibernateDAO<Customer, Long>
        implements GenericDAO<Customer, Long> {
}