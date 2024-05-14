package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.OrderModel;
import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Repository.BarRepository;
import co.edu.unbosque.Rutila.Repository.OrderRepository;
import co.edu.unbosque.Rutila.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    private BarRepository barRepository;
    private UserRepository userRepository;


    @Transactional
    public OrderModel saveOrder(OrderModel order){
        logger.info("Ingreso al metodo");
        OrderModel orderModel= orderRepository.save(order);
        logger.info("El metodo guardo exitosamente");
        return orderModel;
    }

    public OrderModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<OrderModel> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderModel existingOrder = optionalOrder.get();
            existingOrder.setDeletedAt(deleted);
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }


    public List<OrderModel> searchByBarName(String name){
       int id=  barRepository.findBynameAndDeletedAtIsNull(name).getId();
        return orderRepository.findByIdusersAndDeletedAtIsNull(id);

    }

    public List<OrderModel> searchByUserName(String name){
int id = userRepository.findByNameAndDeletedAtIsNull(name).getId();
        return orderRepository.findByIdpubsAndDeletedAtIsNull(id);
    }

}
