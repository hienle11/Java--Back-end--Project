package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sales_invoice_details")
public class SalesInvoiceDetail extends AbstractEntity<Long> {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull(message = "product in sale invoice detail must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Sales_Product"))
    private Product product;

    @Column
    @NotNull(message = "quantity in sale invoice detail must not be null")
    private long quantity;

    @Column(name = "sub_total")
    private double subTotal;

    @JsonIgnore
    @ManyToOne
    @NotNull(message = "sales invoice must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Sales_Invoice"))
    private SalesInvoice salesInvoice;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double price) {
        this.subTotal = price;
    }

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }
}
