package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.BarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents a repository for managing BarModel entities.
 * It extends the JpaRepository interface, providing CRUD operations for
 * BarModel.
 */
@Repository
public interface BarRepository extends JpaRepository<BarModel, Integer> {

    /**
     * Retrieves a BarModel by its location, if it is not deleted.
     *
     * @param location the location of the bar
     * @return the BarModel with the specified location, or null if not found or
     *         deleted
     */
    BarModel findByLocationAndDeletedAtIsNull(String location);

    /**
     * Retrieves a BarModel by its name, if it is not deleted.
     *
     * @param name the name of the bar
     * @return the BarModel with the specified name, or null if not found or deleted
     */
    BarModel findBynameAndDeletedAtIsNull(String name);

    /**
     * Retrieves all BarModels that are not deleted.
     *
     * @return a list of all BarModels that are not deleted
     */
    List<BarModel> findAllByDeletedAtIsNull();
}
