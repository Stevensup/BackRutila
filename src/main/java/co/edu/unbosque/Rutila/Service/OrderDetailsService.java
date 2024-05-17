package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import co.edu.unbosque.Rutila.Repository.OrderDetailsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    private final Logger logger = LoggerFactory.getLogger(OrderDetailsService.class);


    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsModel saveOrderDetail (OrderDetailsModel orderDetail){
        logger.info("Ingreso al metodo");
        OrderDetailsModel orderDetailsModel = orderDetailsRepository.save(orderDetail);
        logger.info("Se guardo exitosamente");
        return orderDetailsModel;
    }



    public OrderDetailsModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<OrderDetailsModel> optionalOrder= orderDetailsRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderDetailsModel existingOrder = optionalOrder.get();
            existingOrder.setDeletedAt(deleted);
            return orderDetailsRepository.save(existingOrder);
        } else {
            return null;
        }
    }
     public List<OrderDetailsModel> findALl (){
   return orderDetailsRepository.findAllByDeletedAtIsNull();
}

    public OrderDetailsModel actualizarDetails(int id, OrderDetailsModel orderDetailsModel) {
        if (orderDetailsRepository.existsById(id)) {
            orderDetailsModel.setId(id);
            return orderDetailsRepository.save(orderDetailsModel);
        } else {
            return null;
        }
    }
}
