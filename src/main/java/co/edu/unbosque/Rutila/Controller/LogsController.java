package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.LogsModel;
import co.edu.unbosque.Rutila.Service.LogsService;
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
@RequestMapping("/logs")
public class LogsController {


    @Autowired
    private LogsService logsService;



    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de auditoria ", description = "Obtener lista de auditoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "auditoria encontrada"),
            @ApiResponse(responseCode = "404", description = " auditoria no encontrada")
    })
    public ResponseEntity<List<LogsModel>> listarAuditoria() {
        List<LogsModel> logs = logsService.findAll();
        if (logs != null) {
            return ResponseEntity.ok(logs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
