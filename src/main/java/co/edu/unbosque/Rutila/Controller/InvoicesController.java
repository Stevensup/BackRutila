package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.*;
import co.edu.unbosque.Rutila.Service.InvoiceService;
import co.edu.unbosque.Rutila.Service.OrderService;
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

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/invoice")
public class InvoicesController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/registrar")
    @Operation(summary = "Crear factura", description = "crea una factura de acuerdo a un cuerpo de json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = InvoicesController.class))))
    })
    public ResponseEntity<String> crearFactura(@RequestBody InvoiceModel invoiceModel) {
        try {
              invoiceService.saveInvoice(invoiceModel,invoiceModel.getOrder());

            return ResponseEntity.ok("Se inserto la factura");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se inserto la factura");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la factura" + e.getMessage());
        }
    }


    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un Factura existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Factura eliminado exitosamente", content = @Content(schema = @Schema(implementation = InvoiceModel.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrado")
    })
    public ResponseEntity<InvoiceModel> eliminadoLogicoInvoice(@PathVariable int id) {
        InvoiceModel actualizadoInvoice;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);
        actualizadoInvoice=  invoiceService.eliminadoLogico(id, deletedTimestamp );
        if (actualizadoInvoice != null) {
            return ResponseEntity.ok(actualizadoInvoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/fecha/{dates}")
    @Operation(summary = "Obtener Factura por su fecha", description = "Obtener los Facturas por sus fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturas encontrado"),
            @ApiResponse(responseCode = "404", description = "Facturas no encontrado")
    })
    public ResponseEntity<InvoiceModel> obtenerFacturasPorDates(@PathVariable String dates) {
      InvoiceModel invoice = invoiceService.searchBydates(dates);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de factura ", description = "Obtener lista de factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "factura encontrados"),
            @ApiResponse(responseCode = "404", description = " factura no encontrados")
    })
    public ResponseEntity<List<InvoiceModel>> listarFacturas() {
        List<InvoiceModel> invoice= invoiceService.findAll();
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar una factura", description = "Actualiza una fatura  existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura actualizado exitosamente", content = @Content(schema = @Schema(implementation = InvoiceModel.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<InvoiceModel> actualizarInvoice(@PathVariable int id, @RequestBody InvoiceModel invoiceModel) {

        invoiceModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        InvoiceModel actualizarInvoice = invoiceService.actualizarInvoice(id, invoiceModel);
        if (actualizarInvoice != null) {
            return ResponseEntity.ok(actualizarInvoice);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
