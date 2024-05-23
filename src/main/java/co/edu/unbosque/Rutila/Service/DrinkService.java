package co.edu.unbosque.Rutila.Service;

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

/**
 * This class represents a service for managing drinks.
 */
@Service
public class DrinkService {
    private final Logger logger = LoggerFactory.getLogger(DrinkService.class);
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private TypeDrinkRepository typeDrinkRepository;

    /**
     * Saves a new drink in the database.
     * 
     * @param drink The drink to be saved.
     * @return The saved drink.
     */
    @Transactional
    public DrinkModel saveDrink(DrinkModel drink) {
        logger.info("Entro al metodo de guardar bebidas" + drink);
        DrinkModel newDrink = drinkRepository.save(drink);

        return newDrink;
    }

    /**
     * Updates an existing drink in the database.
     * 
     * @param id    The ID of the drink to be updated.
     * @param drink The updated drink.
     * @return The updated drink if it exists, null otherwise.
     */
    public DrinkModel updateDrink(int id, DrinkModel drink) {
        if (drinkRepository.existsById(id)) {
            drink.setId(id);
            return drinkRepository.save(drink);
        } else {
            return null;
        }
    }

    /**
     * Performs a logical deletion of a drink by setting its deletedAt timestamp.
     * 
     * @param id      The ID of the drink to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The deleted drink if it exists, null otherwise.
     */
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

    /**
     * Adds inventory to a drink.
     * 
     * @param nombre     The name of the drink.
     * @param inventario The amount of inventory to be added.
     * @return The updated drink if it exists, null otherwise.
     */
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

    /**
     * Searches for a drink by its price.
     * 
     * @param price The price of the drink.
     * @return The found drink if it exists, null otherwise.
     */
    public DrinkModel searchByPrice(double price) {
        return drinkRepository.findByPriceAndDeletedAtIsNull(price);
    }

    /**
     * Searches for a drink by its name.
     * 
     * @param name The name of the drink.
     * @return The found drink if it exists, null otherwise.
     */
    public DrinkModel searchByName(String name) {
        return drinkRepository.findByNameAndDeletedAtIsNull(name);
    }

    /**
     * Searches for drinks by their type.
     * 
     * @param type The type of the drinks.
     * @return A list of drinks with the specified type.
     */
    public List<DrinkModel> searchByType(String type) {
        int id = typeDrinkRepository.findByTypes(type).getId();
        return drinkRepository.findByIdtypeAndDeletedAtIsNull(id);
    }

    /**
     * Retrieves all drinks from the database.
     * 
     * @return A list of all drinks.
     */
    public List<DrinkModel> findAll() {
        List<DrinkModel> drinks = drinkRepository.findAllByDeletedAtIsNull();
        for (DrinkModel drink : drinks) {
            String type = typeDrinkRepository.findByIdAndDeletedAtIsNull(drink.getIdtype()).getTypes();

            drink.setTipo(type);
        }
        return drinks;
    }

    /**
     * Updates an existing drink in the database.
     * 
     * @param id         The ID of the drink to be updated.
     * @param drinkModel The updated drink.
     * @return The updated drink if it exists, null otherwise.
     */
    public DrinkModel actualizarDrinks(int id, DrinkModel drinkModel) {
        if (drinkRepository.existsById(id)) {
            drinkModel.setId(id);
            return drinkRepository.save(drinkModel);
        } else {
            return null;
        }
    }
}
