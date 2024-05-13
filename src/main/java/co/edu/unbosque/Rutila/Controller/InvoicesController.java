package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Service.InvoiceService;
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

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/invoice")
public class InvoicesController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/registrar")
    @Operation(summary = "Crear factura", description = "crea una factura de acuerdo a un cuerpo de json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = InvoicesController.class))))
    })
    public ResponseEntity<String> crearFactura(@RequestBody InvoiceModel invoice) {
        try {
            InvoiceModel nuevoInvoice = invoiceService.saveInvoice(invoice);
            return ResponseEntity.ok("Se inserto la factura");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se inserto la factura");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la factura"+ e.getMessage());
        }
    }


}
