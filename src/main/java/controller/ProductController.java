package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.GenericService;
import service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController extends AbstractCRUDController<Product, Long>{

    @Autowired
    @Qualifier("productService")
    GenericService productService;

    @Override
    protected GenericService getService() {
        return productService;
    }
}
