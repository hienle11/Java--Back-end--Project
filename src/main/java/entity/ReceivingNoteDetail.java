package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "inventory_receiving_note_details")
public class ReceivingNoteDetail extends AbstractEntity<Long> {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @NotNull(message = "receiving note must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ReceivingNotes"))
    private ReceivingNote receivingNote;

    @ManyToOne
    @NotNull(message = "product must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_NoteDetail_Product"))
    private Product product;

    @Column
    @NotNull
    @Min(value = 1, message = "quantity must be greater than 0")
    private long quantity;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public ReceivingNote getReceivingNote() {
        return receivingNote;
    }

    public void setReceivingNote(ReceivingNote receivingNote) {
        this.receivingNote = receivingNote;
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
}