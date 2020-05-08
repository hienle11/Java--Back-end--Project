package service;

import dao.GenericDAO;
import entity.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

// this is the abstract class of service beans, which contains all CRUD implementations in service layer
@Transactional
public abstract class AbstractCRUDService <Entity extends AbstractEntity, ID extends Serializable> 
        implements GenericService<Entity, ID> {
    
    protected abstract GenericDAO getDao();
    
    public Entity findById(ID id) {
        return (Entity) getDao().findById(id);
    }

    public List<Entity> findAll() {
        return getDao().findAll();
    }

    public List<Entity> findByPage(int limit, int offset) {
        return getDao().findByPage(limit, offset);
    }

    public Entity create(Entity entity) {
        return (Entity) getDao().create(entity);
    }

    public Entity update(Entity entity) {
        return (Entity) getDao().update(entity);
    }

    public void delete(Entity entity) {
        getDao().delete(entity);
    }

    public void deleteById(ID id) {
        getDao().deleteById(id);
    }
}
