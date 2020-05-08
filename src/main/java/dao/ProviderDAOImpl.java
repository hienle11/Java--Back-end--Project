package dao;

import entity.Provider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("providerDAO")
public class ProviderDAOImpl extends AbstractHibernateDAO<Provider, Long> {
}