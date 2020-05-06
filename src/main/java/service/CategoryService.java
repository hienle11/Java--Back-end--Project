package service;

import dao.CategoryDAO;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService extends AbstractCRUDService<Category, Long>
{
    @Autowired
    CategoryService(CategoryDAO categoryDAO) {
        super(categoryDAO);
    }
}
