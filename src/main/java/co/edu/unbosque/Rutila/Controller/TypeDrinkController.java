package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import co.edu.unbosque.Rutila.Service.TypeDrinkService;
import io.swagger.v3.oas.annotations.Operation;
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


@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/typeDrink")
public class TypeDrinkController {

@Autowired
private TypeDrinkService typeDrinkService;


    @PostMapping("/registrar")
    @Operation(summary = "Agregar tipo de bebidas", description = "Agrega el objeto typedrink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TypeDrink guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el typedrink")
    })
    public ResponseEntity<String> guardarTipoBebida(@RequestBody TypeDrinkModel type) {
        try{
            type.setCreatedAt(new Timestamp(System.currentTimeMillis()));
           typeDrinkService.saveTypeDrink(type);
            return ResponseEntity.ok("Tipo de bebida guardado con éxito");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("No se inserto el tipo de bebida "+type);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la tipo de bebida "+ e.getMessage());
        }


    }


    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un tipo de bebida existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Tipo de bar eliminado exitosamente", content = @Content(schema = @Schema(implementation = TypeDrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de bar no encontrado")
    })
    public ResponseEntity<TypeDrinkModel> eliminadoLogico(@PathVariable int id) {
       TypeDrinkModel actualizadoType;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);
        actualizadoType= typeDrinkService.eliminadoLogico(id,deletedTimestamp);

        if (actualizadoType != null) {
            return ResponseEntity.ok(actualizadoType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de Tipo de bebida ", description = "Obtener lista de Tipo de bebida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de bebida  encontrados"),
            @ApiResponse(responseCode = "404", description = " Tipo de bebida no encontrados")
    })
    public ResponseEntity<List<TypeDrinkModel>> listarTipoBebida() {
        List<TypeDrinkModel> type= typeDrinkService.findAll();
        if (type != null) {
            return ResponseEntity.ok(type);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar los  tipos de bebida", description = "Actualiza los ordenes de bebida existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de orden actualizado exitosamente", content = @Content(schema = @Schema(implementation = TypeDrinkModel.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de orden  no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<TypeDrinkModel> actualizarDetails(@PathVariable int id, @RequestBody TypeDrinkModel typeDrinkModel) {
        TypeDrinkModel actualizarTipoBebida = typeDrinkService.actualizarType(id, typeDrinkModel);
        if (actualizarTipoBebida != null) {
            return ResponseEntity.ok(actualizarTipoBebida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}