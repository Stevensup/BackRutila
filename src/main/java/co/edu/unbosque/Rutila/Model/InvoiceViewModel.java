package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "invoice_view")
public class InvoiceViewModel {

    @Id
    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "drink_name")
    private String drinkName;

    @Column(name = "drink_price")
    private Double drinkPrice;

    @Column(name = "pub_name")
    private String pubName;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "invoice_date")
    private String invoiceDate;
}
