package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import co.edu.unbosque.Rutila.Model.OrderModel;
import co.edu.unbosque.Rutila.Repository.DrinkRepository;
import co.edu.unbosque.Rutila.Repository.OrderDetailsRepository;
import co.edu.unbosque.Rutila.Repository.OrderRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the service layer for managing order details.
 */
@Service
public class OrderDetailsService {
    private final Logger logger = LoggerFactory.getLogger(OrderDetailsService.class);

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private DrinkRepository drinkRepository;

    /**
     * Saves an order detail and updates the corresponding order's creation
     * timestamp.
     * 
     * @param orderDetail The order detail to be saved.
     * @param order       The order to be updated.
     * @return The saved order detail.
     */
    public OrderDetailsModel saveOrderDetail(OrderDetailsModel orderDetail, OrderModel order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
     DrinkModel drink= drinkRepository.findByIdAndDeletedAtIsNull(orderDetail.getId_drink());
     drink.setAvailability(drink.getAvailability()-orderDetail.getAmount());
     drink.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
         drinkRepository.save(drink);
        OrderModel savedOrder = orderRepository.save(order);

        OrderDetailsModel orderDetailsModel = orderDetailsRepository.save(orderDetail);
        logger.info("Se guardo exitosamente");
        return orderDetailsModel;
    }

    /**
     * Performs a logical deletion of an order detail by setting the deleted
     * timestamp.
     * 
     * @param id      The ID of the order detail to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The updated order detail.
     */
    public OrderDetailsModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<OrderDetailsModel> optionalOrder = orderDetailsRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderDetailsModel existingOrder = optionalOrder.get();
            existingOrder.setDeletedAt(deleted);
            return orderDetailsRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    /**
     * Retrieves all order details that have not been deleted.
     * 
     * @return A list of order details.
     */
    public List<OrderDetailsModel> findALl() {
        return orderDetailsRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates an existing order detail.
     * 
     * @param id                The ID of the order detail to be updated.
     * @param orderDetailsModel The updated order detail.
     * @return The updated order detail, or null if the order detail does not exist.
     */
    public OrderDetailsModel actualizarDetails(int id, OrderDetailsModel orderDetailsModel) {
        if (orderDetailsRepository.existsById(id)) {
            orderDetailsModel.setId(id);
            return orderDetailsRepository.save(orderDetailsModel);
        } else {
            return null;
        }
    }
}
