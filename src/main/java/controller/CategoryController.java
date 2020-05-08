package controller;

import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CategoryService;
import service.GenericService;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractCRUDController<Category, Long> {

    @Autowired
    @Qualifier("categoryService")
    GenericService categoryService;

    @Override
    protected GenericService getService() {
        return categoryService;
    }
}
