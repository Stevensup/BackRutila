package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for managing orders in the Rutila
 * application.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * OrderModel entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

    /**
     * Retrieves a list of orders by the given publication ID, where the deletedAt
     * field is null.
     *
     * @param pubs The publication ID to search for.
     * @return A list of orders matching the given publication ID.
     */
    List<OrderModel> findByIdpubsAndDeletedAtIsNull(int pubs);

    /**
     * Retrieves a list of orders by the given user ID, where the deletedAt field is
     * null.
     *
     * @param users The user ID to search for.
     * @return A list of orders matching the given user ID.
     */
    List<OrderModel> findByIdusersAndDeletedAtIsNull(int users);

    /**
     * Retrieves a list of all orders where the deletedAt field is null.
     *
     * @return A list of all orders.
     */
    List<OrderModel> findAllByDeletedAtIsNull();
}
