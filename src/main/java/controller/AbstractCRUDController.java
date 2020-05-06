package controller;

import entity.AbstractEntity;
import exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import service.AbstractCRUDService;
import service.GenericService;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCRUDController <Entity extends AbstractEntity, ID extends Serializable> {

    private GenericService service;

    AbstractCRUDController(GenericService service) {
        this.service = service;
    }

    @GetMapping
    public List<Entity> findAllEntities() {
        return service.findAll();
    }

    @GetMapping("/{entityId}")
    public Entity findEntityById(@PathVariable ID entityId) {
        Entity theEntity = (Entity) service.findById(entityId);
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entityId);
        }
        return theEntity;
    }

    @PostMapping
    public Entity createEntity(@RequestBody Entity entity) {
        return (Entity) service.create(entity);
    }

    @PutMapping
    public Entity updateEntity(@RequestBody Entity entity) {
        return (Entity) service.update(entity);
    }

    @DeleteMapping
    public void deleteEntity(@RequestBody Entity entity) {
        service.delete(entity);
    }

    @DeleteMapping("/{entityId")
    public void deleteEntityById(@PathVariable ID entityId) {
        service.deleteById(entityId);
    }
}
