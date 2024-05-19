package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDrinkRepository extends JpaRepository<TypeDrinkModel,Integer> {

    TypeDrinkModel findByTypes(String type);

    List<TypeDrinkModel> findAllByDeletedAtIsNull();


    TypeDrinkModel findByIdAndDeletedAtIsNull(int id);
}
