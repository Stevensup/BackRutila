package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Time;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "drinks")
public class DrinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private double price;
    private int availability;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false) // Corrige el nombre de la columna según la tabla
    private TypeDrinkModel type;

     @JsonIgnore
    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
    private List<OrderDetailsModel> orderDetails;





    @Override
    public String toString() {
        return "DrinkModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", types=" + type +
                ", price=" + price +
                ", availability=" + availability +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
