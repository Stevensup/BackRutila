package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * The OrderModel class represents an order in the system.
 * It contains information about the order, such as its ID, dates, creation and
 * update timestamps, and associated details.
 * The class also includes annotations for database mapping and relationships
 * with other entities.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderModel {

   /**
    * The ID of the order.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   /**
    * The dates of the order in the format HH:mm.
    */
   @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
   private String dates;

   /**
    * The timestamp when the order was created.
    */
   @JsonIgnore
   @Column(name = "created_at", nullable = false, updatable = false)
   private Timestamp createdAt;

   /**
    * The timestamp when the order was last updated.
    */
   @JsonIgnore
   @Column(name = "updated_at", nullable = true)
   private Timestamp updatedAt;

   /**
    * The timestamp when the order was deleted.
    */
   @JsonIgnore
   @Column(name = "deleted_at", nullable = true)
   private Timestamp deletedAt;

   /**
    * The ID of the customer associated with the order.
    */
   @Column(name = "id_customer")
   private int idcustomers;

   /**
    * The ID of the publication associated with the order.
    */
   @Column(name = "id_pub")
   private int idpubs;

   /**
    * The ID of the user associated with the order.
    */
   @Column(name = "id_user")
   private int idusers;



   /**
    * Returns a string representation of the OrderModel object.
    *
    * @return a string representation of the object.
    */
   @Override
   public String toString() {
      return "OrderModel{" +
            "id=" + id +
            ", dates=" + dates +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", deletedAt=" + deletedAt +
            '}';
   }
}
