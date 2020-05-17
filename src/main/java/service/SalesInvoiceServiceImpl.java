package service;

import dao.GenericDAO;
import dao.SalesInvoiceDAO;
import dto.RevenueReport;
import entity.SalesInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesInvoiceServiceImpl extends AbstractService<SalesInvoice, Long>
        implements GenericService<SalesInvoice, Long>, SalesInvoiceService {

    @Autowired
    @Qualifier("salesInvoiceDAOImpl")
    private GenericDAO salesInvoiceDAO;

    @Autowired
    private SalesInvoiceDAO customSalesInvoiceDAO;

    @Override
    protected GenericDAO getDao() {
        return salesInvoiceDAO;
    }

    public Page<SalesInvoice> searchByPeriod(String startDate, String endDate, Pageable pageable) {
        List<SalesInvoice> wholeResultList = customSalesInvoiceDAO.searchByPeriod(startDate, endDate);
        return getPage(wholeResultList, pageable);
    }

    @Override
    public Page<SalesInvoice> searchByFieldAndPeriod(String startDate, String endDate, String field, String searchKey, Pageable pageable) {
        List<SalesInvoice> wholeResultList = customSalesInvoiceDAO.searchByFieldAndPeriod(startDate, endDate, field, searchKey);
        return getPage(wholeResultList, pageable);
    }

    @Override
    public RevenueReport getTotalRevenue(String startDate, String endDate, String field, String searchKey) {
        List<SalesInvoice> wholeResultList = customSalesInvoiceDAO.searchByFieldAndPeriod(startDate, endDate, field, searchKey);

        RevenueReport revenueReport = new RevenueReport();
        double totalRevenue = 0;
        for(SalesInvoice salesInvoice: wholeResultList) {
            totalRevenue += salesInvoice.getTotal();
        }

        revenueReport.setPeriod("from " + startDate + " to " + endDate);
        revenueReport.setDescription("total revenue of " +field + " id: " + searchKey);
        revenueReport.setTotalRevenue(totalRevenue);

        return revenueReport;
    }
}