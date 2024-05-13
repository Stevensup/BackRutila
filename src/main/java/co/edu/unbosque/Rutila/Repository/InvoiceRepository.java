package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel,Integer> {


}
