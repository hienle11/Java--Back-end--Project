package service;

import dao.GenericDAO;
import entity.SalesInvoice;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SalesInvoiceServiceImpl extends AbstractCRUDService<SalesInvoice, Long> implements GenericService<SalesInvoice, Long> {

    @Autowired
    @Qualifier("salesInvoiceDAOImpl")
    GenericDAO salesInvoiceDAO;

    @Override
    protected GenericDAO getDao() {
        return salesInvoiceDAO;
    }
}