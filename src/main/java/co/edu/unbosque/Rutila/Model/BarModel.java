package co.edu.unbosque.Rutila.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.sql.Time;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import jakarta.validation.constraints.Pattern;


@Data
@Entity
@Getter
@Setter
@Table(name = "pubs")
public class BarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private String phone;
    // Ignorar en la base de datos
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
    private String entrytime;

   // Ignorar en la base de datos
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato debe ser HH:mm")
    private String closingtime;


@JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
@JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
@JsonIgnore
@Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;




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
