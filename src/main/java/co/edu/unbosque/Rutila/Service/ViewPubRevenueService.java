package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.ViewPubRevenueModel;
import co.edu.unbosque.Rutila.Repository.ViewPubRevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a service for retrieving public revenue views.
 */
@Service
public class ViewPubRevenueService {

    @Autowired
    private ViewPubRevenueRepository pubRevenueRepository;

    /**
     * Retrieves all public revenue views.
     * 
     * @return a list of ViewPubRevenueModel objects representing the public revenue
     *         views.
     */
    public List<ViewPubRevenueModel> findAll() {
        return pubRevenueRepository.findAll();
    }
}
