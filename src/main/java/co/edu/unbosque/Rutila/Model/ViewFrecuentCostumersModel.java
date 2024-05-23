package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

/**
 * This class represents a model for the ViewFrecuentCostumersModel entity.
 * It contains the necessary properties to represent a frequent customer.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "frequent_customers")
public class ViewFrecuentCostumersModel {
    /**
     * The unique identifier of the frequent customer.
     */
    @JsonIgnore
    @Id
    private int id;

    /**
     * The name of the frequent customer.
     */
    private String name;

    /**
     * The email of the frequent customer.
     */
    private String email;

    /**
     * The total number of orders made by the frequent customer.
     */
    private int total_orders;
}
