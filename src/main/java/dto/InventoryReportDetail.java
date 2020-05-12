package dto;

public class InventoryReportDetail {
    private String productName;

    private long receivedQuantity;

    private long deliveredQuantity;

    private int balance;

    public InventoryReportDetail(String productName, long receivedQuantity, long deliveredQuantity, int balance) {
        this.productName = productName;
        this.receivedQuantity = receivedQuantity;
        this.deliveredQuantity = deliveredQuantity;
        this.balance = balance;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(long receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public long getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(long deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
