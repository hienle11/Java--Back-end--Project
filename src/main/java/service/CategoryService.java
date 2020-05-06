package service;

import dao.CategoryDAO;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractCRUDService<Category, Long>{

    @Autowired
    CategoryService(CategoryDAO categoryDAO) {
        super(categoryDAO);
    }
}
