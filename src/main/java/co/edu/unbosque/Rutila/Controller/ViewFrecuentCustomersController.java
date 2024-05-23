package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.ViewFrecuentCostumersModel;
import co.edu.unbosque.Rutila.Service.ViewFrequentCustomersService;
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
@RequestMapping("/frecuentCustomers")
public class ViewFrecuentCustomersController {

    @Autowired
    private ViewFrequentCustomersService viewFrequentCustomersService;

    /**
     * Retrieves a list of the most frequent customers.
     *
     * @return ResponseEntity<List<ViewFrecuentCostumersModel>> - The response
     *         entity containing the list of most frequent customers.
     *         Returns a 200 status code if the list is found, or a 404 status code
     *         if the list is not found.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de clientes mas frecuente ", description = "Obtener lista de clientes mas frecuentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "clientes mas frecuentes no encontrados"),
            @ApiResponse(responseCode = "404", description = " clientes mas frecuentes no encontrados")
    })
    public ResponseEntity<List<ViewFrecuentCostumersModel>> listarViewFrecuentCustomer() {
        List<ViewFrecuentCostumersModel> frecuentcustomer = viewFrequentCustomersService.findAll();
        if (frecuentcustomer != null) {
            return ResponseEntity.ok(frecuentcustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
