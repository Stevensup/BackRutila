package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import co.edu.unbosque.Rutila.Repository.TypeDrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a service for managing TypeDrink entities.
 */
@Service
public class TypeDrinkService {
    private final Logger logger = LoggerFactory.getLogger(TypeDrinkService.class);

    @Autowired
    private TypeDrinkRepository typeDrinkRepository;

    /**
     * Saves a TypeDrink entity.
     * 
     * @param typeDrink The TypeDrinkModel object to be saved.
     * @return The saved TypeDrinkModel object.
     */
    @Transactional
    public TypeDrinkModel saveTypeDrink(TypeDrinkModel typeDrink) {
        logger.info("entro al metodo de guardado");
        TypeDrinkModel typeDrinkModel = typeDrinkRepository.save(typeDrink);
        logger.info("Se guardo con exito");
        return typeDrinkModel;
    }

    /**
     * Searches for a TypeDrink entity by its type.
     * 
     * @param type The type of the TypeDrink entity to search for.
     * @return The TypeDrinkModel object found, or null if not found.
     */
    public TypeDrinkModel searchByType(String type) {
        return typeDrinkRepository.findByTypes(type);
    }

    /**
     * Performs a logical deletion of a TypeDrink entity.
     * 
     * @param id      The ID of the TypeDrink entity to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The updated TypeDrinkModel object after deletion, or null if the
     *         entity was not found.
     */
    public TypeDrinkModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<TypeDrinkModel> optionalType = typeDrinkRepository.findById(id);

        if (optionalType.isPresent()) {
            TypeDrinkModel existingType = optionalType.get();
            existingType.setDeletedAt(deleted);
            return typeDrinkRepository.save(existingType);
        } else {
            return null;
        }
    }

    /**
     * Retrieves all TypeDrink entities that have not been deleted.
     * 
     * @return A list of TypeDrinkModel objects.
     */
    public List<TypeDrinkModel> findAll() {
        return typeDrinkRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates a TypeDrink entity.
     * 
     * @param id             The ID of the TypeDrink entity to be updated.
     * @param typeDrinkModel The updated TypeDrinkModel object.
     * @return The updated TypeDrinkModel object, or null if the entity was not
     *         found.
     */
    public TypeDrinkModel actualizarType(int id, TypeDrinkModel typeDrinkModel) {
        if (typeDrinkRepository.existsById(id)) {
            typeDrinkModel.setId(id);
            return typeDrinkRepository.save(typeDrinkModel);
        } else {
            return null;
        }
    }
}
