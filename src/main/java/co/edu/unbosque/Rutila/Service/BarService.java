package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Repository.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;

/**
 * This class represents a service for managing Bar entities.
 */
@Service
public class BarService {

    private final Logger logger = LoggerFactory.getLogger(BarService.class);

    @Autowired
    private BarRepository barRepository;

    /**
     * Saves a new Bar entity.
     *
     * @param bar The BarModel object to be saved.
     * @return The saved BarModel object.
     */
    @Transactional
    public BarModel saveBar(BarModel bar) {
        logger.info("Entro el metodo de guardar el bar" + bar);
        BarModel newBar = barRepository.save(bar);
        logger.info("Se guardo el bar" + bar.getName());

        return newBar;
    }

    /**
     * Performs a logical deletion of a Bar entity by setting the deletedAt
     * timestamp.
     *
     * @param id      The ID of the BarModel object to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The updated BarModel object.
     */
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

    /**
     * Updates a Bar entity.
     *
     * @param id       The ID of the BarModel object to be updated.
     * @param barModel The updated BarModel object.
     * @return The updated BarModel object.
     */
    public BarModel actualizarBar(int id, BarModel barModel) {
        if (barRepository.existsById(id)) {
            barModel.setId(id);
            return barRepository.save(barModel);
        } else {
            return null;
        }
    }

    /**
     * Finds a Bar entity by its name.
     *
     * @param name The name of the BarModel object to be found.
     * @return The found BarModel object, or null if not found.
     */
    public BarModel findByName(String name) {
        return barRepository.findBynameAndDeletedAtIsNull(name);
    }

    /**
     * Finds a Bar entity by its location.
     *
     * @param location The location of the BarModel object to be found.
     * @return The found BarModel object, or null if not found.
     */
    public BarModel findByLocation(String location) {
        return barRepository.findByLocationAndDeletedAtIsNull(location);
    }

    /**
     * Retrieves all Bar entities.
     *
     * @return A list of all BarModel objects.
     */
    public List<BarModel> findAll() {
        return barRepository.findAllByDeletedAtIsNull();
    }

}
