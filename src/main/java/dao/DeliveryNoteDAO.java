package dao;

import entity.DeliveryNote;

import java.util.List;

public interface DeliveryNoteDAO  {
    List<DeliveryNote> searchByPeriod(String startDate, String endDate);
}
