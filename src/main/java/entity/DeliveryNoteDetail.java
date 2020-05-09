package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventory_delivery_note_details")
public class DeliveryNoteDetail extends AbstractEntity<Long> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @NotNull(message = "receiving note must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_DeliveryNotes"))
    private DeliveryNote deliveryNote;

    @ManyToOne
    @NotNull(message = "product must not be null")
    @JoinColumn(foreignKey = @ForeignKey(name="FK_DeliveryDetail_Product"))
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

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
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
}
