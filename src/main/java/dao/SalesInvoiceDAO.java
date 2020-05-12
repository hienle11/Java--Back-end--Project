package dao;

import entity.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesInvoiceDAO {
    List<SalesInvoice> searchByPeriod(String startDate, String endDate);
    List<SalesInvoice> searchByFieldAndPeriod(String startDate, String endDate, String field, String searchKey);
}
