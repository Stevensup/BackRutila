package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.CustomersModel;
import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Repository.DrinkRepository;
import co.edu.unbosque.Rutila.Repository.TypeDrinkRepository;
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
    @Autowired
    private TypeDrinkRepository typeDrinkRepository;

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


    public DrinkModel agregarInventario(String nombre, int inventario) {
        // Busca la bebida por su nombre
        DrinkModel drink = drinkRepository.findByNameAndDeletedAtIsNull(nombre);

        if (drink != null) { // Si la bebida existe
            // Actualiza el inventario
            drink.setAvailability(inventario);
            // Guarda los cambios en la base de datos y devuelve la bebida actualizada
            return drinkRepository.save(drink);
        } else {
            // Maneja el caso en que la bebida no existe
            return null;
        }
    }

    public DrinkModel searchByPrice (double price){

        return drinkRepository.findByPriceAndDeletedAtIsNull(price);
    }
    public DrinkModel searchByName (String name){

        return drinkRepository.findByNameAndDeletedAtIsNull(name);
    }
    public List<DrinkModel> searchByType (String type){
        int id = typeDrinkRepository.findByTypes(type).getId();
        return drinkRepository.findByIdtypeAndDeletedAtIsNull(id);
    }


    public List<DrinkModel> findAll (){
        return drinkRepository.findAllByDeletedAtIsNull();
    }

    public DrinkModel actualizarDrinks(int id, DrinkModel drinkModel) {
        if (drinkRepository.existsById(id)) {
            drinkModel.setId(id);
            return drinkRepository.save(drinkModel);
        } else {
            return null;
        }
    }
}

