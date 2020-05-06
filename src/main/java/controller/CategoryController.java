package controller;

import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractCRUDController<Category, Long> {

    @Autowired
    CategoryController(CategoryService service)
    {
        super(service);
    }
}
