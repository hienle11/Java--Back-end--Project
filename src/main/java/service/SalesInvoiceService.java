package service;

import dao.GenericDAO;
import entity.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesInvoiceService{
    Page<SalesInvoice> searchByPeriod(String startDate, String endDate, Pageable pageable);
}
