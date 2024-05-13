package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.*;
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
    private Time dates;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
private List<InvoiceModel> invoices;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailsModel>orderDetail;

    @ManyToOne()
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private CustomersModel customers;

    @ManyToOne()
       @JoinColumn(name="id", insertable = false, updatable = false)
       private BarModel pubs;

    @ManyToOne()
       @JoinColumn(name="id", insertable = false, updatable = false)
       private UserModel users;





    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", dates=" + dates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", pubs=" + pubs +
                '}';
    }
}
