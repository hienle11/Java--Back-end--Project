package dao;

import entity.DeliveryNote;
import entity.ReceivingNote;

import java.util.List;

public interface ReceivingNoteDAO {
    List<ReceivingNote> searchByPeriod(String startDate, String endDate);
}
