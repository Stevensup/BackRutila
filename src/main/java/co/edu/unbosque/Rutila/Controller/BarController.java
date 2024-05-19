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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @ApiResponse(responseCode = "200", description = "Bar guardado con éxito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 200, \"message\": \"Bar registrado con éxito\"}}"))
            }),
            @ApiResponse(responseCode = "400", description = "Error al guardar el bar", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 400, \"message\": \"Error interno del servidor\", \"details\": \"Ocurrió un error inesperado\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> guardarBar(@RequestBody BarModel bar) {
        try {
            bar.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            barService.saveBar(bar);

            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 200);
            estado.put("message", "Bar registrado con éxito");
            successResponse.put("estado", estado);

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se insertó el bar " + bar);

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 400);
            errorDetail.put("message", "Error interno del servidor");
            errorDetail.put("details", e.getMessage());
            errorResponse.put("error", errorDetail);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/nombre/{name}")
    @Operation(summary = "Obtener bares por su nombre", description = "Obtener los bares por sus nombres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 200, \"message\": \"Bar encontrado\", \"data\": { ...detalles del bar... }}}"))
            }),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 404, \"message\": \"Bar no encontrado\", \"details\": \"No se encontró ningún bar con el nombre especificado\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> obtenerBarPorNombre(@PathVariable String name) {
        BarModel bar = barService.findByName(name);
        if (bar != null) {
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 200);
            estado.put("message", "Bar encontrado");
            estado.put("data", bar); // Incluye el bar en los detalles
            successResponse.put("estado", estado);
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 404);
            errorDetail.put("message", "Bar no encontrado");
            errorDetail.put("details", "No se encontró ningún bar con el nombre especificado");
            errorResponse.put("error", errorDetail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("ubicacion/{location}")
    @Operation(summary = "Obtener bares por su ubicación", description = "Obtener los bares por sus ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 200, \"message\": \"Bar encontrado\", \"data\": { ...detalles del bar... }}}"))
            }),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 404, \"message\": \"Bar no encontrado\", \"details\": \"No se encontró ningún bar en la ubicación especificada\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> obtenerBarPorUbicacion(@PathVariable String location) {
        BarModel bar = barService.findByLocation(location);
        if (bar != null) {
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 200);
            estado.put("message", "Bar encontrado");
            estado.put("data", bar); // Incluye el bar en los detalles
            successResponse.put("estado", estado);
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 404);
            errorDetail.put("message", "Bar no encontrado");
            errorDetail.put("details", "No se encontró ningún bar en la ubicación especificada");
            errorResponse.put("error", errorDetail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Lógico", description = "Elimina un bar existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Bar eliminado exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 202, \"message\": \"Bar eliminado exitosamente\", \"data\": { ...detalles del bar... }}}"))
            }),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 404, \"message\": \"Bar no encontrado\", \"details\": \"No se encontró ningún bar con el ID especificado\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> eliminadoLogicoBar(@PathVariable int id) {
        BarModel actualizadoBar;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);

        actualizadoBar = barService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoBar != null) {
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 202);
            estado.put("message", "Bar eliminado exitosamente");
            estado.put("data", actualizadoBar); // Incluye los detalles del bar
            successResponse.put("estado", estado);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(successResponse);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 404);
            errorDetail.put("message", "Bar no encontrado");
            errorDetail.put("details", "No se encontró ningún bar con el ID especificado");
            errorResponse.put("error", errorDetail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de bares", description = "Obtener todos los bares")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bares encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 200, \"message\": \"Bares encontrados\", \"data\": [ ...lista de bares... ]}}"))
            }),
            @ApiResponse(responseCode = "404", description = "Bares no encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 404, \"message\": \"Bares no encontrados\", \"details\": \"No se encontraron bares\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> listarTodosBares() {
        List<BarModel> bars = barService.findAll();
        if (!bars.isEmpty()) {
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 200);
            estado.put("message", "Bares encontrados");
            estado.put("data", bars); // Incluye la lista de bares
            successResponse.put("estado", estado);
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 404);
            errorDetail.put("message", "Bares no encontrados");
            errorDetail.put("details", "No se encontraron bares");
            errorResponse.put("error", errorDetail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un bar", description = "Actualiza un bar existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar actualizado exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": true, \"estado\": {\"code\": 200, \"message\": \"Bar actualizado exitosamente\", \"data\": { ...detalles del bar actualizado... }}}"))
            }),
            @ApiResponse(responseCode = "404", description = "Bar no encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\"success\": false, \"error\": {\"code\": 404, \"message\": \"Bar no encontrado\"}}"))
            })
    })
    public ResponseEntity<Map<String, Object>> actualizarBar(@PathVariable int id, @RequestBody BarModel barModel) {
        BarModel actualizarBar = barService.actualizarBar(id, barModel);
        if (actualizarBar != null) {
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            Map<String, Object> estado = new HashMap<>();
            estado.put("code", 200);
            estado.put("message", "Bar actualizado exitosamente");
            estado.put("data", actualizarBar); // Incluye el bar actualizado
            successResponse.put("estado", estado);
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            Map<String, Object> errorDetail = new HashMap<>();
            errorDetail.put("code", 404);
            errorDetail.put("message", "Bar no encontrado");
            errorResponse.put("error", errorDetail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}














