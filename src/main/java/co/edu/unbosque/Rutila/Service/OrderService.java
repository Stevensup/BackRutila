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

/**
 * This class represents the service layer for orders in the Rutila application.
 * It provides methods to interact with the order repository and perform various
 * operations on orders.
 */
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

    /**
     * Saves an order in the database.
     * 
     * @param order The order to be saved.
     * @return The saved order.
     */
    @Transactional
    public OrderModel saveOrder(OrderModel order) {
        logger.info("Ingreso al metodo");

        OrderModel orderModel = orderRepository.save(order);
        logger.info("El metodo guardo exitosamente");
        return orderModel;
    }




    /**
     * Performs a logical deletion of an order by setting its deletedAt timestamp.
     * 
     * @param id      The ID of the order to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The updated order with the deletedAt timestamp set.
     */
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

    /**
     * Searches for orders by the name of the bar.
     * 
     * @param name The name of the bar.
     * @return A list of orders associated with the bar.
     */
    public List<OrderModel> searchByBarName(String name) {
        int id = barRepository.findBynameAndDeletedAtIsNull(name).getId();
        return orderRepository.findByIdusersAndDeletedAtIsNull(id);
    }

    /**
     * Searches for orders by the name of the user.
     * 
     * @param name The name of the user.
     * @return A list of orders associated with the user.
     */
    public List<OrderModel> searchByUserName(String name) {
        int id = userRepository.findByNameAndDeletedAtIsNull(name).getId();
        return orderRepository.findByIdpubsAndDeletedAtIsNull(id);
    }

    /**
     * Retrieves all orders from the database.
     * 
     * @return A list of all orders.
     */
    public List<OrderModel> findAll() {
        return orderRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates an existing order in the database.
     * 
     * @param id         The ID of the order to be updated.
     * @param orderModel The updated order data.
     * @return The updated order.
     */
    public OrderModel actualizarOrder(int id, OrderModel orderModel) {
        if (orderRepository.existsById(id)) {
            orderModel.setId(id);
            return orderRepository.save(orderModel);
        } else {
            return null;
        }
    }
}
