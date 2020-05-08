package service;

import dao.GenericDAO;
import dao.ProviderDAO;
import entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("providerService")
public class ProviderService extends AbstractCRUDService<Provider, Long>{

    @Autowired
    @Qualifier("providerDAO")
    GenericDAO providerDAO;

    @Override
    protected GenericDAO getDao() {
        return providerDAO;
    }
}
