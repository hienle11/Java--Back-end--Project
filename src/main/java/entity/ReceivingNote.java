package entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventory_receiving_note")
public class ReceivingNote extends AbstractEntity<Long>
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @NotNull(message = "staff responsible for this order must not be null.")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ReceivingNote_Staff"))
    private Staff staff;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull(message = "received order must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ReceivingNote_Order"))
    private Order order;

    @OneToMany(mappedBy = "receivingNote", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<ReceivingNoteDetail> receivingNoteDetails;

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

    public List<ReceivingNoteDetail> getReceivingNoteDetails() {
        return receivingNoteDetails;
    }

    public void setReceivingNoteDetails(List<ReceivingNoteDetail> receivingNoteDetails) {
        this.receivingNoteDetails = receivingNoteDetails;
    }

    public Long getOrder() {
        if (order != null){
            return order.getId();
        } else {
            return Long.valueOf(0);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
