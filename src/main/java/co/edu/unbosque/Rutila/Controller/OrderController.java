package co.edu.unbosque.Rutila.Controller;


import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.CustomersModel;
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

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BarService barService;

    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    @Operation(summary = "Crear Orden", description = "crea una Orden de acuerdo a un cuerpo de json.")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la orden" + e.getMessage());
        }
    }



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

    @GetMapping("Nombre-bar/{name}")
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

    @GetMapping("Nombre-usuario/{name}")
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

    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de ordenes ", description = "Obtener lista de ordenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ordenes encontrados"),
            @ApiResponse(responseCode = "404", description = " ordenes no encontrados")
    })
    public ResponseEntity<List<OrderModel>> listarOrdenes() {
        List<OrderModel> order= orderService.findAll();
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Orden", description = "Actualiza una Orden existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada exitosamente", content = @Content(schema = @Schema(implementation = OrderModel.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<OrderModel> actualizarOrden(@PathVariable int id, @RequestBody OrderModel orderModel) {
        OrderModel actualizarOrden = orderService.actualizarOrder(id, orderModel);
        if (actualizarOrden != null) {
            return ResponseEntity.ok(actualizarOrden);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
