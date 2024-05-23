package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import jakarta.validation.constraints.Pattern;

/**
 * The BarModel class represents a bar in the system.
 * It contains information about the bar's ID, name, location, phone number,
 * entry time, closing time,
 * creation timestamp, update timestamp, and deletion timestamp.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "pubs")
public class BarModel {

    /**
     * The ID of the bar.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the bar.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The location of the bar.
     */
    @Column(nullable = false)
    private String location;

    /**
     * The phone number of the bar.
     */
    private String phone;

    /**
     * The entry time of the bar.
     * This field is ignored in the database.
     */
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
    private String entrytime;

    /**
     * The closing time of the bar.
     * This field is ignored in the database.
     */
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
    private String closingtime;

    /**
     * The creation timestamp of the bar.
     * This field is ignored in JSON serialization.
     */
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * The update timestamp of the bar.
     * This field is ignored in JSON serialization.
     */
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    /**
     * The deletion timestamp of the bar.
     * This field is ignored in JSON serialization.
     */
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * Returns a string representation of the BarModel object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "BarModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", entrytime=" + entrytime +
                ", closingtime=" + closingtime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
