package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents a repository for managing InvoiceModel objects.
 * It extends the JpaRepository interface, providing CRUD operations for
 * InvoiceModel entities.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {

    /**
     * Retrieves an InvoiceModel object by its id, if it is not deleted.
     *
     * @param id The id of the invoice to retrieve.
     * @return The InvoiceModel object with the specified id, or null if it is
     *         deleted.
     */
    InvoiceModel findByIdAndDeletedAtIsNull(int id);

    /**
     * Retrieves an InvoiceModel object by its dates, if it is not deleted.
     *
     * @param dates The dates of the invoice to retrieve.
     * @return The InvoiceModel object with the specified dates, or null if it is
     *         deleted.
     */
    InvoiceModel findByDatesAndDeletedAtIsNull(String dates);

    /**
     * Retrieves all InvoiceModel objects that are not deleted.
     *
     * @return A list of all InvoiceModel objects that are not deleted.
     */
    List<InvoiceModel> findAllByDeletedAtIsNull();
}
