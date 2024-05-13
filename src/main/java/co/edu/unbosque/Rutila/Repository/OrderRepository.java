package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Integer> {
    List<OrderModel> findByPubsName(String name);


    List<OrderModel> findByUsersName(String name);
}
