package dao;

import entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO
        extends AbstractHibernateDAO<Product, Long>
        implements GenericDAO<Product, Long> {
}