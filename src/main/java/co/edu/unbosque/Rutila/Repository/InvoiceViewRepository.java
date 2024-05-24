package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.InvoiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceViewRepository extends JpaRepository<InvoiceViewModel,Integer> {

    InvoiceViewModel findByInvoiceNumber(int id);
}
