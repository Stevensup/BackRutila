package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import co.edu.unbosque.Rutila.Repository.OrderDetailsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

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

}
