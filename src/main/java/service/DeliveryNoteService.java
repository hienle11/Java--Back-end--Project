package service;

import entity.DeliveryNote;
import entity.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryNoteService {
    Page<DeliveryNote> searchByPeriod(String startDate, String endDate, Pageable pageable);
}
