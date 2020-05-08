package service;

import dao.CategoryDAO;
import dao.GenericDAO;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// this is the Product Service bean, which provides CRUD API for Category
@Service
@Qualifier("categoryService")
public class CategoryService extends AbstractCRUDService<Category, Long>{

    @Autowired
    @Qualifier("categoryDAO")
    GenericDAO categoryDAO;

    @Override
    protected GenericDAO getDao() {
        return categoryDAO;
    }
}
