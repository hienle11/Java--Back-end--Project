package dao;

import entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("productDAO")
public class ProductDAO extends AbstractHibernateDAO<Product, Long> {
}

