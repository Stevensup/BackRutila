package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Getter
@Setter
@Table(name = "invoice")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double total;
    private Timestamp dates;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;

   @ManyToOne()
    @JoinColumn(name="id", insertable = false, updatable = false)
    private OrderModel orders;


    @Override
    public String toString() {
        return "InvoiceModel{" +
                "id=" + id +
                ", total=" + total +
                ", dates=" + dates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", orders=" + orders +
                '}';
    }
}
