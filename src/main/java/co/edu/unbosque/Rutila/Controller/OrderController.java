package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.CustomersModel;
import co.edu.unbosque.Rutila.Model.InvoiceModel;
import co.edu.unbosque.Rutila.Model.OrderModel;
import co.edu.unbosque.Rutila.Service.BarService;
import co.edu.unbosque.Rutila.Service.ClientService;
import co.edu.unbosque.Rutila.Service.OrderService;
import co.edu.unbosque.Rutila.Service.UserService;
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
 * This class is the controller for handling order-related operations.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * Service for handling order-related operations.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Service for handling client-related operations.
     */
    @Autowired
    private ClientService clientService;

    /**
     * Service for handling bar-related operations.
     */
    @Autowired
    private BarService barService;

    /**
     * Service for handling user-related operations.
     */
    @Autowired
    private UserService userService;

    /**
     * Creates a new order.
     * 
     * @param order The order to be created.
     * @return The response entity with the result of the operation.
     */
    @PostMapping("/registrar")
    @Operation(summary = "Crear Orden", description = "Crea una Orden de acuerdo a un cuerpo de json.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderController.class))))
    })
    public ResponseEntity<String> crearOrden(@RequestBody OrderModel order) {
        try {
            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            OrderModel nuevoOrder = orderService.saveOrder(order);
            return ResponseEntity.ok("Se inserto la ordene");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se inserto la orden");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la orden" + e.getMessage());
        }
    }

    /**
     * Soft deletes an existing order.
     * 
     * @param id The ID of the order to be deleted.
     * @return The response entity with the result of the operation.
     */
    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina una orden existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Orden eliminado exitosamente", content = @Content(schema = @Schema(implementation = OrderModel.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrado")
    })
    public ResponseEntity<OrderModel> eliminadoLogico(@PathVariable int id) {
        OrderModel actualizadoOrder;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);

        actualizadoOrder = orderService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoOrder != null) {
            return ResponseEntity.ok(actualizadoOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves orders by bar name.
     * 
     * @param name The name of the bar.
     * @return The response entity with the list of orders.
     */
    @GetMapping("/buscar/nombre-bar/{name}")
    @Operation(summary = "Obtener ordenes por su nombre de bar", description = "Obtener las ordenes por nombre de abr")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrado"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrado")
    })
    public ResponseEntity<List<OrderModel>> obtenerBarPorNombreBar(@PathVariable String name) {
        List<OrderModel> order = orderService.searchByBarName(name);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves orders by user name.
     * 
     * @param name The name of the user.
     * @return The response entity with the list of orders.
     */
    @GetMapping("/buscar/nombre-usuario/{name}")
    @Operation(summary = "Obtener ordenes por su nombre de usuario", description = "Obtener las ordenes por nombre de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrado"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrado")
    })
    public ResponseEntity<List<OrderModel>> obtenerBarPorNombreUser(@PathVariable String name) {
        List<OrderModel> order = orderService.searchByUserName(name);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of all orders.
     * 
     * @return The response entity with the list of orders.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de ordenes ", description = "Obtener lista de ordenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ordenes encontrados"),
            @ApiResponse(responseCode = "404", description = "ordenes no encontrados")
    })
    public ResponseEntity<List<OrderModel>> listarOrdenes() {
        List<OrderModel> order = orderService.findAll();
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing order.
     * 
     * @param id         The ID of the order to be updated.
     * @param orderModel The updated order.
     * @return The response entity with the result of the operation.
     */
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar una Orden", description = "Actualiza una Orden existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada exitosamente", content = @Content(schema = @Schema(implementation = OrderModel.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<OrderModel> actualizarOrden(@PathVariable int id, @RequestBody OrderModel orderModel) {
        orderModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        OrderModel actualizarOrden = orderService.actualizarOrder(id, orderModel);
        if (actualizarOrden != null) {
            return ResponseEntity.ok(actualizarOrden);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
