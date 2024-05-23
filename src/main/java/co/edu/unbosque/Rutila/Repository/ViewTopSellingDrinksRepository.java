package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.ViewTopSellingDrinksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for accessing and manipulating data
 * related to the top selling drinks view.
 * It extends the JpaRepository interface, providing CRUD (Create, Read, Update,
 * Delete) operations for the ViewTopSellingDrinksModel entity.
 */
@Repository
public interface ViewTopSellingDrinksRepository extends JpaRepository<ViewTopSellingDrinksModel, Integer> {
}
