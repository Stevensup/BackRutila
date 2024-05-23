package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.ViewFrecuentCostumersModel;
import co.edu.unbosque.Rutila.Repository.ViewFrecuentCustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a service for retrieving frequent customers' data.
 */
@Service
public class ViewFrequentCustomersService {

    @Autowired
    private ViewFrecuentCustomersRepository viewFrecuentCustomersRepository;

    /**
     * Retrieves a list of all frequent customers.
     *
     * @return a list of ViewFrecuentCostumersModel objects representing the
     *         frequent customers.
     */
    public List<ViewFrecuentCostumersModel> findAll() {
        return viewFrecuentCustomersRepository.findAll();
    }
}
