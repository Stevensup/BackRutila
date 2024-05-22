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


    @Transactional
    public OrderModel createOrderWithInvoice(OrderModel order, InvoiceModel invoice) {
        // Save the order first to get its ID
        OrderModel savedOrder = orderRepository.save(order);

        // Set the saved order to each order detail and save it
        for (OrderDetailsModel orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(savedOrder);
            orderDetailsRepository.save(orderDetail);
        }

        // Set the saved order to the invoice and save it
        invoice.setOrder(savedOrder);
        invoiceRepository.save(invoice);

        // Set the invoice to the saved order
        savedOrder.setInvoice(invoice);

        return savedOrder;
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
