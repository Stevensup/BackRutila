package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for managing OrderDetailsModel
 * objects.
 * It extends the JpaRepository interface, providing CRUD operations for
 * OrderDetailsModel entities.
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsModel, Integer> {

    /**
     * Retrieves all OrderDetailsModel objects that have not been deleted.
     *
     * @return a list of OrderDetailsModel objects with deletedAt set to null
     */
    List<OrderDetailsModel> findAllByDeletedAtIsNull();

    /**
     * Retrieves all OrderDetailsModel objects associated with a specific order ID.
     *
     * @param orderId the ID of the order
     * @return a list of OrderDetailsModel objects with the given order ID
     */
    List<OrderDetailsModel> findByOrderId(int orderId);
}
