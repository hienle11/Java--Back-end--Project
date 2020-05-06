package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class ProductController
{

    @Autowired
    private ProductService productService;

//    @RequestMapping(path = "students", method = RequestMethod.GET)
//    public List<Student> getStudentsByName(@RequestParam String s){
//        return productService.findStudents(s);
//    }
//
//    @RequestMapping(path = "students", method = RequestMethod.POST)
//    public Student addStudent(@RequestBody Student student) {
//        return productService.saveStudent(student);
//    }

    @PostMapping("/products")
    public Product createAProduct(@RequestBody Product product) {
        return productService.create(product);
    }
}
