package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Repository.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BarService {

    private final Logger logger = LoggerFactory.getLogger(BarService.class);


    @Autowired
    private BarRepository barRepository;


    @Transactional
    public BarModel saveBar(BarModel bar){
        logger.info("Entro el metodo de guardar el bar"+bar);
        BarModel newBar = barRepository.save(bar);
        logger.info("Se guardo el bar"+bar.getName());

        return newBar;
    }

    public BarModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<BarModel> optionalBar = barRepository.findById(id);

        if (optionalBar.isPresent()) {
            BarModel bar = optionalBar.get();
            bar.setDeletedAt(deleted);
            return barRepository.save(bar);
        } else {
            return null;
        }
    }

    public BarModel actualizarBar(int id, BarModel barModel) {
        if (barRepository.existsById(id)) {
            barModel.setId(id);
            return barRepository.save(barModel);
        } else {
            return null;
        }
    }


    public BarModel findByName(String name){
        return barRepository.findBynameAndDeletedAtIsNull(name);

    }


    public BarModel findByLocation(String location){

        return barRepository.findByLocationAndDeletedAtIsNull(location);
    }


    public List<BarModel> findAll(){
        return barRepository.findAllByDeletedAtIsNull();

    }




}
