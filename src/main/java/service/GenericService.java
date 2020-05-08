package service;

import entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

// this is the interface of the generic service, which shows how to implement all CRUD service functions
public interface GenericService<Entity extends AbstractEntity, ID extends Serializable> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity create(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);

    void deleteById(ID id);

    List<Entity> findByPage(int limit, int offset);
}
