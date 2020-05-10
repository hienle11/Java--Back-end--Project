package service;

import dao.GenericDAO;
import entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

// this is the abstract class of service beans, which contains all CRUD implementations in service layer
@Transactional
public abstract class AbstractService<Entity extends AbstractEntity, ID extends Serializable>
        implements GenericService<Entity, ID> {
    
    protected abstract GenericDAO getDao();
    
    public Entity findById(ID id) {
        return (Entity) getDao().findById(id);
    }

    public List<Entity> findAll() {
        return getDao().findAll();
    }

    public Page<Entity> findPaginated(Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Entity> paginatedList = getDao().findByPage(pageSize, startItem);

        Page<Entity> entityPage
                = new PageImpl(paginatedList, PageRequest.of(currentPage, pageSize), getDao().getTotalSize());

        return entityPage;
    }

    public Entity create(Entity entity) {
        return (Entity) getDao().create(entity);
    }

    public Entity update(Entity entity) {
        return (Entity) getDao().update(entity);
    }

    public void deleteById(ID id) {
        getDao().deleteById(id);
    }

    public Page<Entity> searchPaginated(String field, String searchKey, Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Entity> paginatedList = getDao().searchPaginated(field, searchKey);
        Page<Entity> entityPage
                = new PageImpl(paginatedList, PageRequest.of(currentPage, pageSize), 100);

        return entityPage;
    }
}
