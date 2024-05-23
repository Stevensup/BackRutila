package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

/**
 * The InvoiceModel class represents an invoice in the system.
 * It contains information such as the invoice ID, total amount, dates, creation
 * and update timestamps,
 * and the associated order.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "invoices")
public class InvoiceModel {

    /**
     * The ID of the invoice.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The total amount of the invoice.
     */
    private double total;

    /**
     * The dates of the invoice in the format "MM/dd/yyyy HH:mm".
     */
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2} (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$", message = "El formato debe ser MM/dd/yyyy HH:mm")
    private String dates;

    /**
     * The timestamp when the invoice was created.
     */
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * The timestamp when the invoice was last updated.
     */
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    /**
     * The timestamp when the invoice was deleted.
     */
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * The associated order of the invoice.
     */
    @Column(name="id_order")
    private int id_order;

    /**
     * Returns a string representation of the InvoiceModel object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "InvoiceModel{" +
                "id=" + id +
                ", total=" + total +
                ", dates=" + dates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", orders=" + id_order +
                '}';
    }
}
