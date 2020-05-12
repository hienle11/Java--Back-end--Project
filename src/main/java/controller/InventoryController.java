package controller;

import dto.InventoryReport;
import dto.InventoryReportDetail;
import entity.DeliveryNote;
import entity.DeliveryNoteDetail;
import entity.ReceivingNote;
import entity.ReceivingNoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteService;
import service.GenericService;
import service.ReceivingNoteService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    ReceivingNoteService receivingNoteService;

    @Autowired
    DeliveryNoteService deliveryNoteService;

    @GetMapping()
    public InventoryReport getInventoryReport(
            @RequestParam String startDate, @RequestParam String endDate) {

        List<ReceivingNote> receivingNotes = receivingNoteService.searchByPeriod(startDate, endDate);
        List<DeliveryNote> deliveryNotes = deliveryNoteService.searchByPeriod(startDate, endDate);

        HashMap<Long, Long[]> productDetails = new HashMap<>();
        Long[] quantities; // quantities[0] is for receiving, quantities[1] is for delivery, quantities[2] is for balance

        for(ReceivingNote receivingNote: receivingNotes) {
            for(ReceivingNoteDetail receivingNoteDetail: receivingNote.getReceivingNoteDetails()) {
                // check if the product id exists in the hash map or not
                if (productDetails.containsKey(receivingNoteDetail.getProduct().getId())) {
                // if yes, calculate new received quantity
                    quantities = productDetails.get(receivingNoteDetail.getProduct().getId());
                    quantities[0] += receivingNoteDetail.getQuantity();
                } else {
                    quantities = new Long[2];
                    quantities[0] = receivingNoteDetail.getQuantity();
                    quantities[1] = 0L;
                }
                productDetails.put(receivingNoteDetail.getProduct().getId(), quantities);
            }
        }

        for(DeliveryNote deliveryNote: deliveryNotes) {
            for(DeliveryNoteDetail deliveryNoteDetail: deliveryNote.getDeliveryNoteDetails()) {
                // check if the product id exists in the hash map or not
                if (productDetails.containsKey(deliveryNoteDetail.getProduct().getId())) {
                    // if yes, calculate new received quantity
                    quantities = productDetails.get(deliveryNoteDetail.getProduct().getId());
                    quantities[1] += deliveryNoteDetail.getQuantity();
                } else {
                    quantities = new Long[2];
                    quantities[1] = deliveryNoteDetail.getQuantity();
                    quantities[0] = 0L;
                }
                productDetails.put(deliveryNoteDetail.getProduct().getId(), quantities);
            }
        }



        InventoryReport inventoryReport = new InventoryReport();
        inventoryReport.setPeriod("from " + startDate + " to " + endDate);

        List<InventoryReportDetail> inventoryReportDetails = new ArrayList<>();

        productDetails.forEach((productId, values) -> {
            inventoryReportDetails.add(new InventoryReportDetail(productId.toString(), values[0], values[1], (int)(values[0] - values[1])));
        });
        inventoryReport.setInventoryDetails(inventoryReportDetails);

        return inventoryReport;
    }

}
