package service;

import dao.GenericDAO;
import entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends AbstractService<Provider, Long> {

    @Autowired
    @Qualifier("providerDAOImpl")
    GenericDAO providerDAO;

    @Override
    protected GenericDAO getDao() {
        return providerDAO;
    }
}
