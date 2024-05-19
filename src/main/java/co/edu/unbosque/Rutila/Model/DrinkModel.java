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
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    @Column(name = "id_type")
    private int  idtype;

    @Transient
    private String tipo;


}
