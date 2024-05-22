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

@Service
public class InvoiceService {
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    public InvoiceModel saveInvoice(InvoiceModel invoice, OrderModel orderModel){
        orderModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        OrderModel savedOrder = orderRepository.save(orderModel);


        invoice.setOrder(savedOrder);
        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        InvoiceModel invoiceModel = invoiceRepository.save(invoice);
        logger.info("Se guardo la factura");
        return invoiceModel;
    }

    public  InvoiceModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<InvoiceModel> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            InvoiceModel existingInvoice = optionalInvoice.get();
            existingInvoice.setDeletedAt(deleted);
            return invoiceRepository.save(existingInvoice);
        } else {
            return null;
        }
    }


        public InvoiceModel searchByid(int id){

        return invoiceRepository.findByIdAndDeletedAtIsNull(id);
        }



        public InvoiceModel searchBydates(String dates){
        return invoiceRepository.findByDatesAndDeletedAtIsNull(dates);
        }


        public List<InvoiceModel> findAll(){
        return invoiceRepository.findAllByDeletedAtIsNull();
        }


    public InvoiceModel actualizarInvoice(int id, InvoiceModel invoiceModel) {
        if (invoiceRepository.existsById(id)) {
            invoiceModel.setId(id);
            return invoiceRepository.save(invoiceModel);
        } else {
            return null;
        }
    }




}
