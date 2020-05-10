package service;

import entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

// this is the interface of the generic service, which shows how to implement all CRUD service functions
public interface GenericService<Entity extends AbstractEntity, ID extends Serializable> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity create(Entity entity);

    Entity update(Entity entity);

    void deleteById(ID id);

    Page<Entity> findPaginated(Pageable pageable);

    Page<Entity> searchPaginated(String field, String searchKey, Pageable pageable);
}
