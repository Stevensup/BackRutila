package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.OrderDetailsModel;
import co.edu.unbosque.Rutila.Service.DrinkService;
import co.edu.unbosque.Rutila.Service.OrderDetailsService;
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
@RequestMapping("/detailsOrder")
public class DetailOrderController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private DrinkService drinkService;

    @PostMapping("/registrar")
    @Operation(summary = "Agregar Detalle de ordenes", description = "Agrega Detalle de ordenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de ordenes guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar detalle de ordenes")
    })
    public ResponseEntity<String> guardarDetalleOrdenes(@RequestBody OrderDetailsModel order) {
        try {
            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            orderDetailsService.saveOrderDetail(order, order.getOrder());
            return ResponseEntity.ok("Detalle de orden guardado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se inserto el tipo de bebida " + order);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la tipo de bebida " + e.getMessage());
        }

    }

    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un Detalle de orden existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Detalle de orden eliminado exitosamente", content = @Content(schema = @Schema(implementation = OrderDetailsModel.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de orde no encontrado")
    })
    public ResponseEntity<OrderDetailsModel> eliminadoLogico(@PathVariable int id) {
        OrderDetailsModel actualizadoOrder;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);
        actualizadoOrder = orderDetailsService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoOrder != null) {
            return ResponseEntity.ok(actualizadoOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de detalles de ordenes ", description = "Obtener lista de detalles de ordenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "detalles de ordenes encontrados"),
            @ApiResponse(responseCode = "404", description = "detalles de ordenes no encontrados")
    })
    public ResponseEntity<List<OrderDetailsModel>> listarDetalles() {
        List<OrderDetailsModel> orders = orderDetailsService.findALl();
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar los  detalles de una orden", description = "Actualiza los detalles de una orden existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de orden actualizado exitosamente", content = @Content(schema = @Schema(implementation = OrderDetailsModel.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de orden  no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })

    public ResponseEntity<OrderDetailsModel> actualizarDetails(@PathVariable int id,
            @RequestBody OrderDetailsModel orderDetailsModel) {
        orderDetailsModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        OrderDetailsModel actualizarDetalles = orderDetailsService.actualizarDetails(id, orderDetailsModel);
        if (actualizarDetalles != null) {
            return ResponseEntity.ok(actualizarDetalles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
