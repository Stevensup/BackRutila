package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the model for the view of pub revenue.
 * It contains the name and total revenue of a pub.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "pub_revenue")
public class ViewPubRevenueModel {

    /**
     * The id of the pub.
     */
    @JsonIgnore
    @Id
    private int id;
    /**
     * The name of the pub.
     */
    private String name;

    /**
     * The total revenue of the pub.
     */
    private Double total_revenue;
}
