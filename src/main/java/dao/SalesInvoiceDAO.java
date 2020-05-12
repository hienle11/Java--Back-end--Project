package dao;

import entity.SalesInvoice;

import java.util.List;

public interface SalesInvoiceDAO {
    List<SalesInvoice> searchByPeriod(String startDate, String endDate);
}
