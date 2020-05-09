package dao;

import entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl extends AbstractHibernateDAO<Product, Long> {
}

