package service;

import entity.ReceivingNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceivingNoteService {
    Page<ReceivingNote> searchByPeriod(String startDate, String endDate, Pageable pageable);
}
