package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.ViewTopSellingDrinksModel;

import co.edu.unbosque.Rutila.Service.ViewTopSellingService;
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
@RequestMapping("/topSelling")
public class ViewTopSellingController {

    @Autowired
    private ViewTopSellingService viewTopSellingService;

    /**
     * Retrieves a list of top selling drinks.
     *
     * This method is responsible for retrieving a list of top selling drinks from
     * the database.
     * It returns a ResponseEntity object containing the list of
     * ViewTopSellingDrinksModel objects.
     * If the list is not empty, it returns a response with HTTP status code 200
     * (OK) and the list of drinks.
     * If the list is empty, it returns a response with HTTP status code 404 (Not
     * Found).
     *
     * @return ResponseEntity<List<ViewTopSellingDrinksModel>> - The response entity
     *         containing the list of top selling drinks.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de  bebidas mas bebidas ", description = "Obtener lista debebidas mas bebidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "bebidas mas bebidas no encontrados"),
            @ApiResponse(responseCode = "404", description = " bebidas mas bebidas no encontrados")
    })
    public ResponseEntity<List<ViewTopSellingDrinksModel>> listarViewTopSelling() {
        List<ViewTopSellingDrinksModel> frecuentTopSelling = viewTopSellingService.findAll();
        if (frecuentTopSelling != null) {
            return ResponseEntity.ok(frecuentTopSelling);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
