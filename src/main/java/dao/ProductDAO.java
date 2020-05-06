package dao;

import entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO
        extends AbstractHibernateDAO<Product, Long>
        implements IGenericDAO<Product, Long>{
}
