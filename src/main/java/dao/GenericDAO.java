package dao;

import entity.AbstractEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GenericDAO<Entity extends AbstractEntity, ID> {

    public Entity findById(ID id);

    public List<Entity> findAll();

    public Entity create(Entity entity);

    public Entity update(Entity entity);

    public void delete(Entity entity);

    public void deleteById(ID id);

}
