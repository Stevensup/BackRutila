package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkModel,Integer> {
    List<DrinkModel> findByType_Id(int id);

DrinkModel findByPrice(double price);

DrinkModel findByName(String name);

}
