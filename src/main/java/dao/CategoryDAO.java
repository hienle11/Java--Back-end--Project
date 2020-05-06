package dao;

import entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO
        extends AbstractHibernateDAO<Category, Long>
        implements GenericDAO<Category, Long> {
}