package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents a repository for managing TypeDrinkModel objects.
 * It extends the JpaRepository interface, providing CRUD operations for
 * TypeDrinkModel entities.
 */
@Repository
public interface TypeDrinkRepository extends JpaRepository<TypeDrinkModel, Integer> {

    /**
     * Retrieves a TypeDrinkModel object by its type.
     *
     * @param type the type of the drink
     * @return the TypeDrinkModel object with the specified type, or null if not
     *         found
     */
    TypeDrinkModel findByTypes(String type);

    /**
     * Retrieves all TypeDrinkModel objects that are not marked as deleted.
     *
     * @return a list of TypeDrinkModel objects that are not marked as deleted
     */
    List<TypeDrinkModel> findAllByDeletedAtIsNull();

    /**
     * Retrieves a TypeDrinkModel object by its id, if it is not marked as deleted.
     *
     * @param id the id of the drink
     * @return the TypeDrinkModel object with the specified id and not marked as
     *         deleted, or null if not found
     */
    TypeDrinkModel findByIdAndDeletedAtIsNull(int id);
}
