package controller;

import dto.InventoryReportDetail;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import service.DeliveryNoteService;
import service.GenericService;
import service.ReceivingNoteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    ReceivingNoteService receivingNoteService;

    @Autowired
    DeliveryNoteService deliveryNoteService;

    @Autowired
    @Qualifier("productServiceImpl")
    GenericService productService;

    @GetMapping()
    public Page<InventoryReportDetail> getInventoryReport(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam(required = false) Optional<Long> size,
            @RequestParam(required = false) Optional<Long> page) {

        Long currentPage = page.orElse(Long.valueOf(0));
        Long pageSize = size.orElse(Long.valueOf(5));

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

        List<InventoryReportDetail> inventoryReportDetails = new ArrayList<>();

        productDetails.forEach((productId, values) -> {
            Product product = (Product) productService.findById(productId);
            inventoryReportDetails.add(new InventoryReportDetail(product.getName(), values[0], values[1], (int)(values[0] - values[1])));
        });

        List<InventoryReportDetail> paginatedList = new ArrayList<>();
        int count = 0;
        int limit = pageSize.intValue();
        for (InventoryReportDetail eachDetail: inventoryReportDetails) {
            count++;
            if (count >= pageSize.intValue() * currentPage.intValue()) {
                paginatedList.add(eachDetail);
                limit--;
            }
            if (limit == 0) {
                break;
            }
        }
        return new PageImpl(paginatedList, PageRequest.of(currentPage.intValue(), pageSize.intValue()), inventoryReportDetails.size());
    }

}
