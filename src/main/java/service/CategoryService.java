package service;

import dao.CategoryDAO;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// this is the Product Service bean, which provides CRUD API for Category
@Service
public class CategoryService extends AbstractCRUDService<Category, Long> implements GenericService<Category, Long>{

    @Autowired
    CategoryService(CategoryDAO dao) {
        super(dao);
    }
}
