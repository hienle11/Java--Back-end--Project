package controller;

import com.sun.tools.javac.jvm.Gen;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.GenericService;
import service.ProductService;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController extends AbstractCRUDController<Product, Long>{

    @Autowired
    ProductController(ProductService productService) {
        super(productService);
    }

//    @RequestMapping(path = "students", method = RequestMethod.GET)
//    public List<Student> getStudentsByName(@RequestParam String s){
//        return productService.findStudents(s);
//    }
//
//    @RequestMapping(path = "students", method = RequestMethod.POST)
//    public Student addStudent(@RequestBody Student student) {
//        return productService.saveStudent(student);
//    }
}
