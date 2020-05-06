package service;

import dao.CategoryDAO;
import dao.ProductDAO;
import entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CoT on 10/14/17.
 */
@Service
public class ProductService extends AbstractCRUDService<Product, Long> {

    @Autowired
    ProductService(ProductDAO productDAO) {
        super(productDAO);
    }
//    public Student saveStudent(Student student) {
//        sessionFactory.getCurrentSession().save(student);
//        return student;
//    }
//
//    public Student getStudent(int id){
//        Query query = sessionFactory.getCurrentSession().createQuery("from Student where id:id");
//        query.setInteger("id", id);
//
//        return (Student) query.uniqueResult();
//
//    }
//
//
//    public List<Student> getAllStudents(){
//        Query query = sessionFactory.getCurrentSession().createQuery("from Student");
//        return query.list();
//
//    }
//
//    public List<Student> findStudents(String name){
//       Query query = sessionFactory.getCurrentSession().createQuery("from Student s where s.name like :name");
//
//       query.setString("name", "%"+name+"%");
//
//       return query.list();
//    }

}
