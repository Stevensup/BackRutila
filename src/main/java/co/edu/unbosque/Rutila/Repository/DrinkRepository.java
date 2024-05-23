package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for managing DrinkModel entities.
 * It extends the JpaRepository interface, providing CRUD operations for
 * DrinkModel.
 */
@Repository
public interface DrinkRepository extends JpaRepository<DrinkModel, Integer> {

    /**
     * Retrieves a list of DrinkModel entities by their type ID, where the deletedAt
     * field is null.
     *
     * @param id The type ID to search for.
     * @return A list of DrinkModel entities.
     */
    List<DrinkModel> findByIdtypeAndDeletedAtIsNull(int id);

    /**
     * Retrieves a DrinkModel entity by its price, where the deletedAt field is
     * null.
     *
     * @param price The price to search for.
     * @return A DrinkModel entity.
     */
    DrinkModel findByPriceAndDeletedAtIsNull(double price);

    /**
     * Retrieves a DrinkModel entity by its name, where the deletedAt field is null.
     *
     * @param name The name to search for.
     * @return A DrinkModel entity.
     */
    DrinkModel findByNameAndDeletedAtIsNull(String name);

    /**
     * Retrieves a list of all DrinkModel entities, where the deletedAt field is
     * null.
     *
     * @return A list of DrinkModel entities.
     */
    List<DrinkModel> findAllByDeletedAtIsNull();


    DrinkModel findByIdAndDeletedAtIsNull(int id);

}
