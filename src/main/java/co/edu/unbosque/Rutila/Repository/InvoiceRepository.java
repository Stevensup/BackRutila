package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel,Integer> {
    InvoiceModel findByIdAndDeletedAtIsNull(int id);
    InvoiceModel findByDatesAndDeletedAtIsNull(String dates);

    List<InvoiceModel> findAllByDeletedAtIsNull();
}
