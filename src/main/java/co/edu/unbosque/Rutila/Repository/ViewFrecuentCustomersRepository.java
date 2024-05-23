package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.ViewFrecuentCostumersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for accessing and manipulating data
 * related to frequent customers views.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * ViewFrecuentCostumersModel entity.
 */
@Repository
public interface ViewFrecuentCustomersRepository extends JpaRepository<ViewFrecuentCostumersModel, Integer> {
}
