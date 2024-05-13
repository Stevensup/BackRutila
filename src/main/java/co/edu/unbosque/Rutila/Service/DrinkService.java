package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {
    private final Logger logger = LoggerFactory.getLogger(DrinkService.class);
    @Autowired
    private DrinkRepository drinkRepository;

    @Transactional
    public DrinkModel saveDrink(DrinkModel drink){
        logger.info("Entro al metodo de guardar bebidas"+drink);
        DrinkModel newDrink = drinkRepository.save(drink);

         return newDrink;
    }

    public  DrinkModel updateDrink (int id, DrinkModel drink){
        if(drinkRepository.existsById(id)){
            drink.setId(id);
            return drinkRepository.save(drink);
        }else{
            return null;
        }
    }
    public DrinkModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<DrinkModel> optionalDrink = drinkRepository.findById(id);

        if (optionalDrink.isPresent()) {
            DrinkModel existingDrink = optionalDrink.get();
            existingDrink.setDeletedAt(deleted);
            return drinkRepository.save(existingDrink);
        } else {
            return null;
        }
    }

    public DrinkModel searchByPrice (double price){

        return drinkRepository.findByPrice(price);
    }
    public DrinkModel searchByName (String name){

        return drinkRepository.findByName(name);
    }
    public List<DrinkModel> searchByType (int type){

        return drinkRepository.findByType_Id(type);
    }


}

