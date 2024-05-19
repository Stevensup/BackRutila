package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Service.BarService;


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
@RequestMapping("/pubs")
public class BarController {
    @Autowired
    private BarService barService;



    @PostMapping("/registrar")
    @Operation(summary = "Agregar bares", description = "Agrega el objeto pubs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el bar")
    })
    public ResponseEntity<String> guardarBar(@RequestBody BarModel bar) {
        try{
            bar.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            barService.saveBar(bar);
            return ResponseEntity.ok("Bar guardado con éxito");
        }catch (Exception e){
           e.printStackTrace();
           System.out.println("No se inserto el bar"+bar);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la factura "+ e.getMessage());
        }


    }


    @GetMapping("/nombre/{name}")
    @Operation(summary = "Obtener bares por su nombre", description = "Obtener los bares por sus nombres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar encontrado"),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado")
    })
    public ResponseEntity<BarModel> obtenerBarPorNombre(@PathVariable String name) {
        BarModel bar = barService.findByName(name);
        if (bar != null) {
            return ResponseEntity.ok(bar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("ubicacion/{location}")
    @Operation(summary = "Obtener bares por su ubicacion", description = "Obtener los bares por sus ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar encontrado"),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado")
    })
    public ResponseEntity<BarModel> obtenerBarPorUbicacion(@PathVariable String location) {
        BarModel bar = barService.findByLocation(location);
        if (bar != null) {
            return ResponseEntity.ok(bar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un bar existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Bar eliminado exitosamente", content = @Content(schema = @Schema(implementation = BarModel.class))),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado")
    })
    public ResponseEntity<BarModel> eliminadoLogicoBar(@PathVariable int id) {
        BarModel actualizadoBar;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);

        actualizadoBar = barService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoBar != null) {
            return ResponseEntity.ok(actualizadoBar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de bares ", description = "Obtener lista de bares")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bares encontrados"),
            @ApiResponse(responseCode = "404", description = "Bares no encontrados")
    })
    public ResponseEntity<List<BarModel>> listarTodosBares() {
       List <BarModel> bar = barService.findAll();
        if (bar != null) {
            return ResponseEntity.ok(bar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un bar", description = "Actualiza un bar existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar actualizado exitosamente", content = @Content(schema = @Schema(implementation = BarModel.class))),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<BarModel> actualizarBar(@PathVariable int id, @RequestBody BarModel barModel) {
        barModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        BarModel actualizarBar = barService.actualizarBar(id, barModel);
        if (actualizarBar != null) {
            return ResponseEntity.ok(actualizarBar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    }














