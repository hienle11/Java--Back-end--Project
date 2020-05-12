package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventory_delivery_note")
public class DeliveryNote extends AbstractEntity<Long>
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull(message = "delivery note details must be provided")
    @OneToMany(mappedBy = "deliveryNote", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<DeliveryNoteDetail> deliveryNoteDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DeliveryNoteDetail> getDeliveryNoteDetails() {
        return deliveryNoteDetails;
    }

    public void setDeliveryNoteDetails(List<DeliveryNoteDetail> deliveryNoteDetails) {
        this.deliveryNoteDetails = deliveryNoteDetails;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
