package service;

import entity.DeliveryNote;
import entity.ReceivingNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReceivingNoteService {
    Page<ReceivingNote> searchByPeriod(String startDate, String endDate, Pageable pageable);
    List<ReceivingNote> searchByPeriod(String startDate, String endDate);
}
