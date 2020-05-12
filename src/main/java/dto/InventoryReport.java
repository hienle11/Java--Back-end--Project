package dto;

import java.io.Serializable;
import java.util.List;

public class InventoryReport implements Serializable {



    String period;

    List<InventoryReportDetail> inventoryDetails;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<InventoryReportDetail> getInventoryDetails() {
        return inventoryDetails;
    }

    public void setInventoryDetails(List<InventoryReportDetail> inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }
}
