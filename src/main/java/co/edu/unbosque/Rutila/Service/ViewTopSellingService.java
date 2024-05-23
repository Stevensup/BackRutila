package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.ViewTopSellingDrinksModel;
import co.edu.unbosque.Rutila.Repository.ViewTopSellingDrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a service for retrieving top selling drinks data.
 * It provides methods to fetch the data from the repository.
 */
@Service
public class ViewTopSellingService {

    @Autowired
    private ViewTopSellingDrinksRepository viewTopSellingDrinksRepository;

    /**
     * Retrieves a list of top selling drinks.
     *
     * @return a list of ViewTopSellingDrinksModel objects representing the top
     *         selling drinks.
     */
    public List<ViewTopSellingDrinksModel> findAll() {
        return viewTopSellingDrinksRepository.findAll();
    }
}
