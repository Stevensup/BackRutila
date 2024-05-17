package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.CustomersModel;
import co.edu.unbosque.Rutila.Service.ClientService;
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
 * This class represents the controller for managing clients in the application.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;


	@PostMapping("/registrar")
	@Operation(summary = "Crear Clientes", description = "crea un cliente de acuerdo a un cuerpo de json.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientController.class))))
	})
	public ResponseEntity<CustomersModel> crearCliente(@RequestBody CustomersModel cliente) {
		cliente.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		CustomersModel nuevoCliente = clientService.saveClient(cliente);

		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}


	@GetMapping("/nombre/{name}")
	@Operation(summary = "Obtener Cliente por Nombre", description = "Obtiene un cliente existente según su nombre.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente", content = @Content(schema = @Schema(implementation = CustomersModel.class))),
			@ApiResponse(responseCode = "412", description = "Precondición fallida")
	})
	public ResponseEntity<CustomersModel> obtenerClientePorNombre(@PathVariable String name) {
		CustomersModel cliente = clientService.searchClientByName(name);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}

	@GetMapping("/celular/{phone}")
	@Operation(summary = "Obtener Cliente por Telefono", description = "Obtiene un cliente existente según su Telefono.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente", content = @Content(schema = @Schema(implementation = CustomersModel.class))),
			@ApiResponse(responseCode = "412", description = "Precondición fallida")
	})
	public ResponseEntity<CustomersModel> obtenerClientePorTelefono(@PathVariable String phone) {
		CustomersModel cliente = clientService.searchClientByPhone(phone);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}



	@GetMapping("/correo/{email}")
	@Operation(summary = "Obtener Cliente por Correo", description = "Obtiene un cliente existente según su correo.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente", content = @Content(schema = @Schema(implementation = CustomersModel.class))),
			@ApiResponse(responseCode = "412", description = "Precondición fallida")
	})
	public ResponseEntity<CustomersModel> obtenerClientePorCorreo(@PathVariable String email) {
		CustomersModel cliente = clientService.searchClientByEmail(email);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}

	@PutMapping("/eliminar/{id}")
	@Operation(summary = "Borrado Logico", description = "Elimina un Cliente existente según su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Cliente eliminado exitosamente", content = @Content(schema = @Schema(implementation = CustomersModel.class))),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	public ResponseEntity<CustomersModel> eliminadoLogicoClient(@PathVariable int id) {
		CustomersModel actualizadoCliente;
		LocalDateTime now = LocalDateTime.now();
		Timestamp deletedTimestamp = Timestamp.valueOf(now);
		actualizadoCliente = clientService.eliminadoLogico(id, deletedTimestamp );

		if (actualizadoCliente != null) {
		return ResponseEntity.ok(actualizadoCliente);
	} else {
		return ResponseEntity.notFound().build();
	}
	}
	@GetMapping("/listar")
	@Operation(summary = "Obtener lista de clientes ", description = "Obtener lista de clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "clientes encontrados"),
			@ApiResponse(responseCode = "404", description = " clientes no encontrados")
	})
	public ResponseEntity<List<CustomersModel>> listarClientes() {
		List<CustomersModel> customer= clientService.findAll();
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un cliente", description = "Actualiza un cliente existente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente", content = @Content(schema = @Schema(implementation = CustomersModel.class))),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
	})
	public ResponseEntity<CustomersModel> actualizarClient(@PathVariable int id, @RequestBody CustomersModel customersModel) {
		CustomersModel actualizarCliente= clientService.actualizarClient(id, customersModel);
		if (actualizarCliente != null) {
			return ResponseEntity.ok(actualizarCliente);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}



