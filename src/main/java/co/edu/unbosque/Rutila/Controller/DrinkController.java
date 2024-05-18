package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.CustomersModel;
import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import co.edu.unbosque.Rutila.Service.DrinkService;
import co.edu.unbosque.Rutila.Service.TypeDrinkService;
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
import java.time.LocalDateTime;
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
    @Autowired
private TypeDrinkService TypedrinkService;

    @PostMapping("/registrar")
    @Operation(summary = "Crear Bebida", description = "crea una bebida de acuerdo a un cuerpo de json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = DrinkController.class))))
    })
    public ResponseEntity<String> crearBebida(@RequestBody DrinkModel drink) {
        try {


            drink.setCreatedAt(new Timestamp(System.currentTimeMillis()));


            DrinkModel nuevoDrink = drinkService.saveDrink(drink);
            return ResponseEntity.ok("Se insertó la bebida");
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante el proceso
            e.printStackTrace();
            System.out.println("No se insertó la bebida");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la bebida: " + e.getMessage());
        }
    }

    @GetMapping("/nombre/{name}")
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

    @GetMapping("/tipo/{type}")
    @Operation(summary = "Obtener bebidas por su tipo", description = "Obtener los bebidas por sus tipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebidas encontrado"),
            @ApiResponse(responseCode = "404", description = "Bebidas no encontrado")
    })
    public ResponseEntity<List<DrinkModel>> obtenerBebidaPorTipo(@PathVariable String type) {
      List <DrinkModel> drink = drinkService.searchByType(type);
        if (drink != null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/precio/{price}")
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

    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina una bebida existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "bebida eliminada exitosamente", content = @Content(schema = @Schema(implementation = DrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "bebida no encontrada")
    })
    public ResponseEntity<DrinkModel> eliminadoLogicoDrink(@PathVariable int id) {
        DrinkModel actualizadoDrink;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);
        actualizadoDrink= drinkService.eliminadoLogico(id, deletedTimestamp );

        if (actualizadoDrink != null) {
            return ResponseEntity.ok(actualizadoDrink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{name}/{availability}")
    @Operation(summary = "Agregar Inventario", description = "Agregar inventario de la bebida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Agregado el inventario", content = @Content(schema = @Schema(implementation = DrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "inventario  no agregado")
    })
    public ResponseEntity<DrinkModel> agregarInventario(@PathVariable String name,int availability) {
        DrinkModel actualizadoDrink;

        actualizadoDrink= drinkService.agregarInventario(name, availability);

        if (actualizadoDrink != null) {
            return ResponseEntity.ok(actualizadoDrink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de bebidas ", description = "Obtener lista de bebidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "bebidas encontrados"),
            @ApiResponse(responseCode = "404", description = "bebidas no encontrados")
    })
    public ResponseEntity<List<DrinkModel>> listarBebidas() {
        List <DrinkModel> drink = drinkService.findAll();
        if (drink != null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar una bebida", description = "Actualiza una bebida existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebida actualizado exitosamente", content = @Content(schema = @Schema(implementation = DrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "Bebida no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<DrinkModel> actualizarDrink(@PathVariable int id, @RequestBody DrinkModel drinkModel) {
        DrinkModel actualizarDrink= drinkService.actualizarDrinks(id, drinkModel);
        if (actualizarDrink != null) {
            return ResponseEntity.ok(actualizarDrink);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}