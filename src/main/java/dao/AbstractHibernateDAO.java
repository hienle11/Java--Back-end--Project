package dao;

import entity.AbstractEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.GenericService;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractHibernateDAO<Entity extends AbstractEntity, ID> {
    @Autowired
    SessionFactory sessionFactory;

    private final Class<Entity> entityClass;

    public AbstractHibernateDAO() {
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Entity findById(ID id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName() +  " where id:id");
        query.setParameter("id", id);

        return (Entity) query.uniqueResult();
    }

    public List<Entity> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName());
        return query.list();
    }

    public Entity create(Entity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public Entity update(Entity entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public void delete(Entity entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void deleteById(ID id) {
        Entity entity = findById(id);
        delete(entity);
    }
}
