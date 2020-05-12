package service;

import dao.GenericDAO;
import entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
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
        List<Entity> wholeResultList = getDao().searchPaginated(field, searchKey);
        return getPage(wholeResultList, pageable);
    }

    protected Page<Entity> getPage(List<Entity> wholeList, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Entity> paginatedList = new ArrayList<>();
        int count = 0;
        int limit = pageSize;
        for (Entity eachEntity: wholeList) {
            count++;
            if (count >= startItem) {
                paginatedList.add(eachEntity);
                limit--;
            }
            if (limit == 0) {
                break;
            }
        }
        return new PageImpl(paginatedList, PageRequest.of(currentPage, pageSize), wholeList.size());
    }
}
