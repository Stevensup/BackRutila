package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.InvoiceViewModel;
import co.edu.unbosque.Rutila.Service.InvoiceViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/invoiceView")
public class InvoiceViewController {

@Autowired
private InvoiceViewService invoiceViewService;



@GetMapping("/id/{id}")
    @Operation(summary = "Obtener Factura por su id", description = "Obtener los Facturas por sus id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturas encontrado"),
            @ApiResponse(responseCode = "404", description = "Facturas no encontrado")
    })
    public ResponseEntity<InvoiceViewModel> obtenerFacturasPorId(@PathVariable int id) {
        InvoiceViewModel invoice = invoiceViewService.findOne(id);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
