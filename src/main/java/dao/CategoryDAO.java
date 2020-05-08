package dao;

import entity.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("categoryDAO")
public class CategoryDAO extends AbstractHibernateDAO<Category, Long> {
}