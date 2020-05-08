package service;


import dao.GenericDAO;
import dao.ProductDAO;
import entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


// this is the Product Service bean, which provides CRUD API for products
@Service
@Qualifier("productService")
public class ProductService extends AbstractCRUDService<Product, Long> {

    @Autowired
    @Qualifier("productDAO")
    GenericDAO productDAO;

    @Override
    protected GenericDAO getDao() {
        return productDAO;
    }
}
