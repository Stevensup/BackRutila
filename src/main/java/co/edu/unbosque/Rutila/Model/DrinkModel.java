package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

/**
 * This class represents a drink in the system.
 * It contains information such as the drink's ID, name, price, availability,
 * creation date, update date, deletion date, type ID, and type.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "drinks")
public class DrinkModel {

    /**
     * The ID of the drink.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the drink.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The price of the drink.
     */
    private double price;

    /**
     * The availability of the drink.
     */
    private int availability;

    /**
     * The creation date of the drink.
     */
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * The update date of the drink.
     */
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    /**
     * The deletion date of the drink.
     */
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * The ID of the drink's type.
     */
    @Column(name = "types")
    private int idtype;

    /**
     * The type of the drink.
     */
    @Transient
    private String tipo;

}
