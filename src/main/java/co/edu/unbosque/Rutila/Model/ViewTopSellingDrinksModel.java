package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a model for the view of top selling drinks.
 * It is used to store information about the name of the drink and the total
 * number of orders.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "top_selling_drinks")
public class ViewTopSellingDrinksModel {

    /**
     * The name of the drink.
     */
    @Id
    private String drink_name;

    /**
     * The total number of orders for the drink.
     */
    private int total_orders;

}
