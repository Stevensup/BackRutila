package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.ViewPubRevenueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for the ViewPubRevenueModel entity.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * entity.
 */
@Repository
public interface ViewPubRevenueRepository extends JpaRepository<ViewPubRevenueModel, Integer> {
}
