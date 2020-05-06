package dao;

import entity.AbstractEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractHibernateDAO<Entity extends AbstractEntity, ID> extends AbstractDAO
{
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

    public Entity create(Entity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}
