package service;

import dto.RevenueReport;
import entity.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesInvoiceService{
    Page<SalesInvoice> searchByPeriod(String startDate, String endDate, Pageable pageable);
    Page<SalesInvoice> searchByFieldAndPeriod(String startDate, String endDate, String field, String searchKey, Pageable pageable);
    RevenueReport getTotalRevenue(String startDate, String endDate, String field, String searchKey);
}
