package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.BarModel;
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

@Service
public class ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public CustomersModel saveClient(CustomersModel client) {
        logger.info("Entry al metodo de save" + client);
        CustomersModel newClient = clientRepository.save(client);
        logger.info("Se guardo el cliente exitosamente");
        return newClient;
    }


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






    public CustomersModel searchClientByName(String name) {

        return clientRepository.findByNameAndDeletedAtIsNull(name);
    }

    public CustomersModel searchClientByPhone(String phone) {

        return clientRepository.findByPhoneAndDeletedAtIsNull(phone);
    }

    public CustomersModel searchClientByEmail(String email) {

        return clientRepository.findByEmailAndDeletedAtIsNull(email);
    }


    public List<CustomersModel> findAll(){

        return clientRepository.findAllByDeletedAtIsNull();
    }

    public CustomersModel actualizarClient(int id, CustomersModel customersModel) {
        if (clientRepository.existsById(id)) {
            customersModel.setId(id);
            return clientRepository.save(customersModel);
        } else {
            return null;
        }
    }

}
