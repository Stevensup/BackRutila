package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    UserModel findByEmail(String email);
}
