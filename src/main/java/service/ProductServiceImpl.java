package service;


import dao.GenericDAO;
import entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


// this is the Product Service bean, which provides CRUD API for products
@Service
public class ProductServiceImpl extends AbstractService<Product, Long> {

    @Autowired
    @Qualifier("productDAOImpl")
    private GenericDAO productDAO;

    @Override
    protected GenericDAO getDao() {
        return productDAO;
    }
}
