package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDrinkRepository extends JpaRepository<TypeDrinkModel,Integer> {

    TypeDrinkModel findByTypes(String type);
}
