package controller;

import entity.AbstractEntity;
import exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import service.GenericService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUDController <Entity extends AbstractEntity, ID extends Serializable> {
    
    protected abstract GenericService getService();

    @GetMapping("/{entityId}")
    public Entity findEntityById(@PathVariable ID entityId) {
        Entity theEntity = (Entity) getService().findById(entityId);
        if (theEntity == null) { // throw exception if the entity is not found in the database
            throw new EntityNotFoundException("The entity id not found - " + entityId);
        }
        return theEntity;
    }

    @GetMapping
    public Page<Entity> findEntitiesByPage(
            @RequestParam(name = "size", required = false) Optional<Long> size,
            @RequestParam(name = "page", required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));

        Page<Entity> entityPage = (Page<Entity>) getService().findPaginated
                (PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));

        return entityPage;
    }


    @PostMapping
    public Entity createEntity(@RequestBody Entity entity) {
        return (Entity) getService().create(entity);
    }

    @PutMapping
    public Entity updateEntity(@RequestBody Entity entity) {
        if (entity.getId() == null) {
            throw new EntityNotFoundException("Please enter the id of the entity ");
        }
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

    @GetMapping("/search")
    public Page<Entity> searchEntity(
            @RequestParam String field, @RequestParam String searchKey,
            @RequestParam(name = "size", required = false) Optional<Long> size,
            @RequestParam(name = "page", required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(1));
        Long pageSize = size.orElse(Long.valueOf(5));

        Page<Entity> entityPage = (Page<Entity>) getService().searchPaginated
                (field, searchKey, PageRequest.of(currentPage.intValue() - 1, pageSize.intValue()));

        return entityPage;

    }
}
