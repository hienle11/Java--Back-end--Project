package service;


import dao.ProductDAO;
import entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// this is the Product Service bean, which provides CRUD API for products
@Service
public class ProductService extends AbstractCRUDService<Product, Long> implements GenericService<Product, Long>{

    @Autowired
    ProductService(ProductDAO dao) {
        super(dao);
    }
}
