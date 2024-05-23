package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.ViewFrecuentCostumersModel;
import co.edu.unbosque.Rutila.Model.ViewPubRevenueModel;
import co.edu.unbosque.Rutila.Service.ViewPubRevenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/pubRevenue")
public class ViewPubRevenueController {

    @Autowired
    private ViewPubRevenueService viewPubRevenueService;

    /**
     * Retrieves a list of bars with the highest revenue.
     *
     * @return ResponseEntity<List<ViewPubRevenueModel>> - The response entity
     *         containing the list of bars with the highest revenue.
     *         Returns a 200 status code if the list is found, or a 404 status code
     *         if the list is not found.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de bares con mas ventas ", description = "Obtener lista de bares con mas ventas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "bares con mas ventas no encontrados"),
            @ApiResponse(responseCode = "404", description = " bares con mas ventas no encontrados")
    })
    public ResponseEntity<List<ViewPubRevenueModel>> listarViewPubRevenue() {
        List<ViewPubRevenueModel> pubRevenue = viewPubRevenueService.findAll();
        if (pubRevenue != null) {
            return ResponseEntity.ok(pubRevenue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
