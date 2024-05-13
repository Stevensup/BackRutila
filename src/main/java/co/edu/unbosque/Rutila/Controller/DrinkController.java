package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class represents the controller for managing drinks in the application.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/drink")
public class DrinkController {
@Autowired
    private DrinkService drinkService;

    @PostMapping("/registrar")
    @Operation(summary = "Crear Bebida", description = "crea una bebida de acuerdo a un cuerpo de json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = DrinkController.class))))
    })
    public ResponseEntity<String> crearBebida(@RequestBody DrinkModel drink) {
        try {
            DrinkModel nuevoDrink = drinkService.saveDrink(drink);
            return ResponseEntity.ok("Se inserto la bebida");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se inserto la bebida");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la bebida"+ e.getMessage());
        }
    }

    @GetMapping("/{name}")
    @Operation(summary = "Obtener bebidas por su nombre", description = "Obtener los bebidas por sus nombres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebidas encontrado"),
            @ApiResponse(responseCode = "404", description = "Bebidas no encontrado")
    })
    public ResponseEntity<DrinkModel> obtenerBebidaPorNombre(@PathVariable String name) {
        DrinkModel drink = drinkService.searchByName(name);
        if (drink != null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{type}")
    @Operation(summary = "Obtener bebidas por su tipo", description = "Obtener los bebidas por sus tipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebidas encontrado"),
            @ApiResponse(responseCode = "404", description = "Bebidas no encontrado")
    })
    public ResponseEntity<List<DrinkModel>> obtenerBebidaPorTipo(@PathVariable int type) {
       List<DrinkModel> drink = drinkService.searchByType(type);
        if (drink != null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{price}")
    @Operation(summary = "Obtener bebidas por su nombre", description = "Obtener los bebidas por sus nombres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebidas encontrado"),
            @ApiResponse(responseCode = "404", description = "Bebidas no encontrado")
    })
    public ResponseEntity<DrinkModel> obtenerBebidaPorPrecio(@PathVariable double price) {
        DrinkModel drink = drinkService.searchByPrice(price);
        if (drink != null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un Cliente existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente eliminado exitosamente", content = @Content(schema = @Schema(implementation = DrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<DrinkModel> eliminadoLogicoDrink(@PathVariable int id, @RequestParam(required = false) Timestamp deleted) {
        DrinkModel actualizadoDrink;

        if (deleted != null) {
            actualizadoDrink = drinkService.eliminadoLogico(id, deleted);
        } else {

            return ResponseEntity.badRequest().build();
        }

        if (actualizadoDrink != null) {
            return ResponseEntity.ok(actualizadoDrink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}