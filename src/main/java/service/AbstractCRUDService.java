package service;

import dao.IGenericDAO;
import entity.AbstractEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractCRUDService <Entity extends AbstractEntity, ID>
{
    @Autowired
    SessionFactory sessionFactory;

    IGenericDAO dao;

    AbstractCRUDService(IGenericDAO dao) {
        this.dao = dao;
    }

    public Entity findById(ID id) {
        return (Entity) dao.findById(id);
    }

    public Entity create(Entity entity) {
        dao.create(entity);
        return entity;
    }
}
