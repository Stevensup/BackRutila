package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsModel,Integer> {
    List<OrderDetailsModel> findAllByDeletedAtIsNull();
}
