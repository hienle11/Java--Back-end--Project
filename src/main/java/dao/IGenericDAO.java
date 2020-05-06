package dao;

import entity.AbstractEntity;
import org.springframework.stereotype.Repository;

public interface IGenericDAO<Entity extends AbstractEntity, ID> {
    public Entity findById(ID id);

    public Entity create(Entity entity);
}
