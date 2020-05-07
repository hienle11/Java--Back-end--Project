package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "order_to_provider")
public class Order extends AbstractEntity<Long> {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @NotNull(message = "staff responsible for this order must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Order_Staff"))
    private Staff staff;

    @ManyToOne
    @NotNull(message = "provider of the order must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Order_Provider"))
    private Provider provider;

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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}