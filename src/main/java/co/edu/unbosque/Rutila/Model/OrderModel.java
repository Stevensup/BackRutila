package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
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


    @JsonIgnore
    @Column(name = "id_customer")
    private int idcustomers;




    @Column(name = "id_pub")
       private int idpubs;




    @Column(name = "id_user")
       private int idusers;








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
