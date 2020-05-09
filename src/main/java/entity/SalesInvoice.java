package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales_invoice")
public class SalesInvoice extends AbstractEntity<Long> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @NotNull(message = "sale staff must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Sales_Staff"))
    private Staff staff;

    @ManyToOne
    @NotNull(message = "customer in sale invoice must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Sales_Customer"))
    private Customer customer;

    @NotNull(message = "sales invoice details must be provided")
    @OneToMany(mappedBy = "salesInvoice", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<SalesInvoiceDetail> salesInvoiceDetails;

    @Column
    private double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStaff() {
        return staff.getName();
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCustomer() {
        return customer.getName();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SalesInvoiceDetail> getSalesInvoiceDetails() {
        return salesInvoiceDetails;
    }

    public void setSalesInvoiceDetails(List<SalesInvoiceDetail> salesInvoiceDetails) {
        this.salesInvoiceDetails = salesInvoiceDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
