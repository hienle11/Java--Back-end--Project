package dao;

import entity.AbstractEntity;

import java.util.List;

public interface GenericDAO<Entity extends AbstractEntity, ID> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity create(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);

    void deleteById(ID id);

}
