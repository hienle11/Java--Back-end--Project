package service;

import dao.ProviderDAO;
import entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService extends AbstractCRUDService<Provider, Long> implements GenericService<Provider, Long>{

    @Autowired
    ProviderService(ProviderDAO dao) {
        super(dao);
    }
}
