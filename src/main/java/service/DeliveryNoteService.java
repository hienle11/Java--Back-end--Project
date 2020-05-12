package service;

import entity.DeliveryNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliveryNoteService {
    Page<DeliveryNote> searchByPeriod(String startDate, String endDate, Pageable pageable);
    List<DeliveryNote> searchByPeriod(String startDate, String endDate);
}
