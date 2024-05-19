package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderOperationRequest {

    private OrderModel order;
    private OrderDetailsModel orderDetail;
    private InvoiceModel invoice;
}
