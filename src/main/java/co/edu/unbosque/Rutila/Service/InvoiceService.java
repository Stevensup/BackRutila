package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InvoiceService {
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public InvoiceModel saveInvoice(InvoiceModel invoice){
        logger.info("Entro al metodo de guardado de factura");
        InvoiceModel invoiceModel = invoiceRepository.save(invoice);
        logger.info("Se guardo la factura");
        return invoiceModel;
    }

    public InvoiceModel updateInvoice(int id,InvoiceModel invoice) {
        if (invoiceRepository.existsById(id)) {
            invoice.setId(id);
            return invoiceRepository.save(invoice);
        } else {
            return null;
        }
    }


        public InvoiceModel searchByid(int id){
        return invoiceRepository.findById(id).orElse(null);
        }







}
