package controller;

import entity.AbstractEntity;
import exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import service.GenericService;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCRUDController <Entity extends AbstractEntity, ID extends Serializable> {
    
    protected abstract GenericService getService();
    
    @GetMapping
    public List<Entity> findAllEntities() {
        List<Entity> entityList = getService().findAll();
        if(entityList.size() == 0) {
            throw new EntityNotFoundException("The table is empty");
        }
        return entityList;
    }

    @GetMapping("/{entityId}")
    public Entity findEntityById(@PathVariable ID entityId) {
        Entity theEntity = (Entity) getService().findById(entityId);
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entityId);
        }
        return theEntity;
    }

    @PostMapping
    public Entity createEntity(@RequestBody Entity entity) {
        return (Entity) getService().create(entity);
    }

    @PutMapping
    public Entity updateEntity(@RequestBody Entity entity) {
        Entity theEntity = (Entity) getService().findById(entity.getId());
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entity.getId());
        }
        return (Entity) getService().update(entity);
    }

    @DeleteMapping
    public void deleteEntity(@RequestBody Entity entity) {
        Entity theEntity = (Entity) getService().findById(entity.getId());
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entity.getId());
        }
        getService().delete(entity);
    }

    @DeleteMapping("/{entityId}")
    public void deleteEntityById(@PathVariable ID entityId) {
        Entity theEntity = (Entity) getService().findById(entityId);
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entityId);
        }
        getService().deleteById(entityId);
    }
}
