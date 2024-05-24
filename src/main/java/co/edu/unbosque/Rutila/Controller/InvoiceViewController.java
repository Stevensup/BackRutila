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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/invoiceView")
public class InvoiceViewController {

@Autowired
private InvoiceViewService invoiceViewService;
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de detalles de la factura ", description = "Obtener lista de detalles de la factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "detalles de factura encontrados"),
            @ApiResponse(responseCode = "404", description = " detalles de factura no encontrados")
    })
    public ResponseEntity<List<InvoiceViewModel>> listarFacturas() {
        List<InvoiceViewModel> invoice = invoiceViewService.findAll();
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
