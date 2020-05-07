package dao;

import entity.Provider;
import org.springframework.stereotype.Repository;

@Repository
public class ProviderDAO
        extends AbstractHibernateDAO<Provider, Long>
        implements GenericDAO<Provider, Long> {
}