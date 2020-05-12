package service;

import dao.GenericDAO;
import dao.SalesInvoiceDAO;
import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesInvoiceServiceImpl extends AbstractService<SalesInvoice, Long>
        implements GenericService<SalesInvoice, Long>, SalesInvoiceService {

    @Autowired
    @Qualifier("salesInvoiceDAOImpl")
    GenericDAO salesInvoiceDAO;

    @Autowired
    SalesInvoiceDAO customSalesInvoiceDAO;

    @Override
    protected GenericDAO getDao() {
        return salesInvoiceDAO;
    }

    public Page<SalesInvoice> searchByPeriod(String startDate, String endDate, Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<SalesInvoice> paginatedList = customSalesInvoiceDAO.searchByPeriod(startDate, endDate, pageSize, startItem);
        Page<SalesInvoice> entityPage
                = new PageImpl(paginatedList, PageRequest.of(currentPage, pageSize), 100);

        return entityPage;
    }
}