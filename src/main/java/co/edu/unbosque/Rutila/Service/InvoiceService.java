package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.*;
import co.edu.unbosque.Rutila.Repository.InvoiceRepository;
import co.edu.unbosque.Rutila.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The InvoiceService class is responsible for handling operations related to
 * invoices.
 * It provides methods for saving, updating, and retrieving invoices from the
 * database.
 */
@Service
public class InvoiceService {
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Saves an invoice and its associated order to the database.
     * 
     * @param invoice    The invoice to be saved.
     * @return The saved invoice.
     */
    @Transactional
    public InvoiceModel saveInvoice(InvoiceModel invoice) {

        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        InvoiceModel invoiceModel = invoiceRepository.save(invoice);
        logger.info("Se guardo la factura");
        return invoiceModel;
    }

    /**
     * Performs a logical deletion of an invoice by setting its deletedAt timestamp.
     * 
     * @param id      The ID of the invoice to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The deleted invoice.
     */
    public InvoiceModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<InvoiceModel> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            InvoiceModel existingInvoice = optionalInvoice.get();
            existingInvoice.setDeletedAt(deleted);
            return invoiceRepository.save(existingInvoice);
        } else {
            return null;
        }
    }

    /**
     * Retrieves an invoice by its ID.
     * 
     * @param id The ID of the invoice to be retrieved.
     * @return The retrieved invoice.
     */
    public InvoiceModel searchByid(int id) {
        return invoiceRepository.findByIdAndDeletedAtIsNull(id);
    }

    /**
     * Retrieves an invoice by its dates.
     * 
     * @param dates The dates of the invoice to be retrieved.
     * @return The retrieved invoice.
     */
    public InvoiceModel searchBydates(String dates) {
        return invoiceRepository.findByDatesAndDeletedAtIsNull(dates);
    }

    /**
     * Retrieves all invoices from the database.
     * 
     * @return A list of all invoices.
     */
    public List<InvoiceModel> findAll() {
        return invoiceRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates an existing invoice in the database.
     * 
     * @param id           The ID of the invoice to be updated.
     * @param invoiceModel The updated invoice.
     * @return The updated invoice.
     */
    public InvoiceModel actualizarInvoice(int id, InvoiceModel invoiceModel) {
        if (invoiceRepository.existsById(id)) {
            invoiceModel.setId(id);
            return invoiceRepository.save(invoiceModel);
        } else {
            return null;
        }
    }
}
