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
@Table(name = "order_details")
public class OrderDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;
    @ManyToOne ()
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private OrderModel orders;

    @ManyToOne()
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private DrinkModel drinks;

    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", orders=" + orders +
                ", drinks=" + drinks +
                '}';
    }
}
