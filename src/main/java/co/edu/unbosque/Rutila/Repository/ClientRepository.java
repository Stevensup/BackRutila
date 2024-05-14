package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The ClienteRepository interface is responsible for managing the persistence
 * of Cliente entities.
 * It extends the JpaRepository interface, providing CRUD operations for the
 * Cliente entity.
 */
@Repository
public interface ClientRepository extends JpaRepository<CustomersModel, Integer> {


    CustomersModel findByNameAndDeletedAtIsNull(String name);


    CustomersModel findByPhoneAndDeletedAtIsNull(String phone);

    CustomersModel findByEmailAndDeletedAtIsNull(String email);



   ;



}
