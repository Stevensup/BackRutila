package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.*;
import co.edu.unbosque.Rutila.Repository.*;
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

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    InvoiceRepository invoiceRepository;


    @Transactional
    public OrderModel saveOrder(OrderModel order){
        logger.info("Ingreso al metodo");
        OrderModel orderModel= orderRepository.save(order);
        logger.info("El metodo guardo exitosamente");
        return orderModel;
    }


    public void createOrder(OrderModel order, OrderDetailsModel orderDetail, InvoiceModel invoice) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);

        // Asignar la orden al detalle de la orden
        orderDetail.setId_order(order.getId());
        orderDetail .setCreatedAt(new Timestamp(System.currentTimeMillis()));
        orderDetailsRepository.save(orderDetail);

        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        invoice.setId_order(order.getId());
        invoiceRepository.save(invoice);
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


    public List<OrderModel>  findAll (){

        return orderRepository.findAllByDeletedAtIsNull();
    }

    public OrderModel actualizarOrder(int id, OrderModel orderModel) {
        if (orderRepository.existsById(id)) {
            orderModel.setId(id);
            return orderRepository.save(orderModel);
        } else {
            return null;
        }
    }


}
