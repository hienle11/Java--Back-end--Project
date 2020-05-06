package service;

import entity.AbstractEntity;

import java.util.List;

public interface GenericService<Entity extends AbstractEntity, ID> {

    public Entity findById(ID id);

    public List<Entity> findAll();

    public Entity create(Entity entity);

    public Entity update(Entity entity);

    public void delete(Entity entity);

    public void deleteById(ID id);
}
