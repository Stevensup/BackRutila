package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Getter
@Setter
@Table(name = "invoices")
public class InvoiceModel {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double total;
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2} (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$", message = "El formato debe ser MM/dd/yyyy HH:mm")
    private String dates;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    private int id_order;


    @Override
    public String toString() {
        return "InvoiceModel{" +
                "id=" + id +
                ", total=" + total +
                ", dates=" + dates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", orders=" +id_order+
                '}';
    }
}
