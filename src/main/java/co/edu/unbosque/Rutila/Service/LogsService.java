package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.LogsModel;
import co.edu.unbosque.Rutila.Repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;


    public List<LogsModel> findAll(){

        return logsRepository.findAllByOrderByIdDesc();
    }
}
