package dao;

import entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericDAO<Entity extends AbstractEntity, ID> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity create(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);

    void deleteById(ID id);

    List<Entity> findByPage(int limit, int offset);

    long getTotalSize();

    List<Entity> searchPaginated(String field, String searchKey);

}
