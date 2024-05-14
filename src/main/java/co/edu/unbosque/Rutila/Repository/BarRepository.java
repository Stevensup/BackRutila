package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.BarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarRepository extends JpaRepository<BarModel,Integer> {


    BarModel findByLocationAndDeletedAtIsNull(String location);

    BarModel findBynameAndDeletedAtIsNull (String name);
}
