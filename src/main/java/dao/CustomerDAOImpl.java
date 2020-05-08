package dao;

import entity.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("customerDAO")
public class CustomerDAOImpl extends AbstractHibernateDAO<Customer, Long> {
}