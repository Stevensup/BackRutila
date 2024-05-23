package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

/**
 * This class represents the CustomersModel entity in the database.
 * It contains the attributes and methods related to a customer.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "customers")
public class CustomersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private String phone;
    private String email;

    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * Returns a string representation of the CustomersModel object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CustomersModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}