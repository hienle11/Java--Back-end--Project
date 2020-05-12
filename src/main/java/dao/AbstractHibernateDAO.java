package dao;

import entity.AbstractEntity;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractHibernateDAO<Entity extends AbstractEntity, ID extends Serializable>
        implements GenericDAO<Entity, ID> {
    @Autowired
    SessionFactory sessionFactory;

    private final Class<Entity> entityClass;

    // initialize the value of entityClass, so that it can be used for later queries
    public AbstractHibernateDAO() {
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public Entity findById(ID id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public long getTotalSize() {
        Query<Long> query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from " + entityClass.getSimpleName());
        return query.uniqueResult();
    }

    public List<Entity> findByPage(int limit, int offset) {
        Query<Entity> query = sessionFactory.getCurrentSession()
                .createQuery("from " + entityClass.getSimpleName());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public Entity create(Entity entity) {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    public Entity update(Entity incomingEntity) {
        Entity currentEntity = sessionFactory.getCurrentSession().get(entityClass, incomingEntity.getId());

        try {
            new BeanUtilsBean() {
                @Override
                public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
                    if (value != null) {
                        super.copyProperty(bean, name, value);
                    }
                }

            }.copyProperties(currentEntity, incomingEntity);
        } catch (Exception exc) {
        } finally {
            sessionFactory.getCurrentSession().merge(currentEntity);
            return currentEntity;
        }
    }

    public void deleteById(ID id) {
        Entity entity = findById(id);
        sessionFactory.getCurrentSession().delete(entity);
    }


    public List<Entity> searchPaginated(String field, String searchKey, int limit, int offset) {
        Query<Entity> query = sessionFactory.getCurrentSession()
                .createQuery("from " + entityClass.getSimpleName()
                + " where str(" + field + ") like '%" + searchKey + "%'");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

}
