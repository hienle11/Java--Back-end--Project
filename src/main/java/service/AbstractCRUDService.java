package service;

import dao.GenericDAO;
import entity.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

// this is the abstract class of service beans, which contains all CRUD implementations in service layer
@Transactional
public abstract class AbstractCRUDService <Entity extends AbstractEntity, ID extends Serializable> {
    private GenericDAO dao;

    AbstractCRUDService(GenericDAO dao) {
        this.dao = dao;
    }

    public Entity findById(ID id) {
        return (Entity) dao.findById(id);
    }

    public List<Entity> findAll() {
        return dao.findAll();
    }

    public Entity create(Entity entity) {
        return (Entity) dao.create(entity);
    }

    public Entity update(Entity entity) {
        return (Entity) dao.update(entity);
    }

    public void delete(Entity entity) {
        dao.delete(entity);
    }

    public void deleteById(ID id) {
        dao.deleteById(id);
    }
}
