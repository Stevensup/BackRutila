package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.InvoiceViewModel;
import co.edu.unbosque.Rutila.Repository.InvoiceViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceViewService {


    @Autowired
    private InvoiceViewRepository invoiceViewRepository;


    public  List<InvoiceViewModel> findAll(){
        return invoiceViewRepository.findAll();
    }
}
