package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.CustomersModel;
import co.edu.unbosque.Rutila.Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the service layer for managing clients.
 */
@Service
public class ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Saves a new client.
     * 
     * @param client The client to be saved.
     * @return The saved client.
     */
    @Transactional
    public CustomersModel saveClient(CustomersModel client) {
        logger.info("Entry al metodo de save" + client);
        CustomersModel newClient = clientRepository.save(client);
        logger.info("Se guardo el cliente exitosamente");
        return newClient;
    }

    /**
     * Performs a logical deletion of a client by setting the deletedAt timestamp.
     * 
     * @param id      The ID of the client to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The updated client with the deletedAt timestamp set.
     */
    public CustomersModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<CustomersModel> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            CustomersModel existingClient = optionalClient.get();
            existingClient.setDeletedAt(deleted);
            return clientRepository.save(existingClient);
        } else {
            return null;
        }
    }

    /**
     * Searches for a client by name.
     * 
     * @param name The name of the client to search for.
     * @return The found client, or null if not found.
     */
    public CustomersModel searchClientByName(String name) {
        return clientRepository.findByNameAndDeletedAtIsNull(name);
    }

    /**
     * Searches for a client by phone number.
     * 
     * @param phone The phone number of the client to search for.
     * @return The found client, or null if not found.
     */
    public CustomersModel searchClientByPhone(String phone) {
        return clientRepository.findByPhoneAndDeletedAtIsNull(phone);
    }

    /**
     * Searches for a client by email.
     * 
     * @param email The email of the client to search for.
     * @return The found client, or null if not found.
     */
    public CustomersModel searchClientByEmail(String email) {
        return clientRepository.findByEmailAndDeletedAtIsNull(email);
    }

    /**
     * Retrieves all clients.
     * 
     * @return A list of all clients.
     */
    public List<CustomersModel> findAll() {
        return clientRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates an existing client.
     * 
     * @param id             The ID of the client to be updated.
     * @param customersModel The updated client data.
     * @return The updated client, or null if the client does not exist.
     */
    public CustomersModel actualizarClient(int id, CustomersModel customersModel) {
        if (clientRepository.existsById(id)) {
            customersModel.setId(id);
            return clientRepository.save(customersModel);
        } else {
            return null;
        }
    }

}
