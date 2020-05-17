package service;

import dao.GenericDAO;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// this is the Product Service bean, which provides CRUD API for Category
@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> {

    @Autowired
    @Qualifier("categoryDAOImpl")
    private GenericDAO categoryDAO;

    @Override
    protected GenericDAO getDao() {
        return categoryDAO;
    }
}
