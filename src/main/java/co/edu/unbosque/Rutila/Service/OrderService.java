package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.OrderModel;
import co.edu.unbosque.Rutila.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    public OrderModel saveOrder(OrderModel order){
        logger.info("Ingreso al metodo");
        OrderModel orderModel= orderRepository.save(order);
        logger.info("El metodo guardo exitosamente");
        return orderModel;
    }

    public List<OrderModel> searchByBarName(String name){
        return orderRepository.findByPubsName(name);

    }

    public List<OrderModel> searchByUserName(String name){
        return orderRepository.findByUsersName(name);
    }

}
