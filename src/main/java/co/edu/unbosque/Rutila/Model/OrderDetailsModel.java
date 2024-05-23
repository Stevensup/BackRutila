package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * The OrderDetailsModel class represents the details of an order in the system.
 * It contains information such as the ID, amount, creation timestamp, update
 * timestamp, and deletion timestamp.
 * It also includes the ID of the associated drink and the order it belongs to.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetailsModel {

    /**
     * The unique identifier of the order details.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The amount of the drink in the order.
     */
    private int amount;

    /**
     * The timestamp when the order details were created.
     */
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * The timestamp when the order details were last updated.
     */
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    /**
     * The timestamp when the order details were deleted.
     */
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * The ID of the associated drink.
     */
    private int id_drink;

    /**
     * The order that the order details belong to.
     */
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private OrderModel order;

    /**
     * Returns a string representation of the OrderDetailsModel object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "OrderDetailsModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
