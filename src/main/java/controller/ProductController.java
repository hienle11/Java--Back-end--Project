package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController extends AbstractCRUDController<Product, Long>{

    @Autowired
    ProductController(ProductService service) {
        super(service);
    }
}
