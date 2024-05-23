package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.LogsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<LogsModel,Integer> {
}
