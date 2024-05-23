package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Service.UserService;
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

/**
 * This class represents the UserController, which handles the HTTP requests
 * related to user operations.
 */
@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * 
     * @param user The UserModel object representing the user to be registered.
     * @return A ResponseEntity with a success message if the user is registered
     *         successfully, or an error message if an exception occurs.
     */
    @PostMapping("/registrar")
    @Operation(summary = "Agregar Usuarios", description = "Agrega el objeto users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el usuarios")
    })
    public ResponseEntity<String> guardarUsuario(@RequestBody UserModel user) {
        try {
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userService.saveUser(user);
            return ResponseEntity.ok("Usuario guardado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se insertó el Usuario" + user);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el usuario " + e.getMessage());
        }
    }

    /**
     * Performs a logical deletion of a user based on their ID.
     * 
     * @param id The ID of the user to be deleted.
     * @return A ResponseEntity with the updated UserModel object if the user is
     *         deleted successfully, or a not found response if the user is not
     *         found.
     */
    @PutMapping("/eliminar/{id}")
    @Operation(summary = "Borrado Logico", description = "Elimina un usuario según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuario eliminado exitosamente", content = @Content(schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UserModel> eliminadoLogicoUser(@PathVariable int id) {
        UserModel actualizadoUser;
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedTimestamp = Timestamp.valueOf(now);

        actualizadoUser = userService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoUser != null) {
            return ResponseEntity.ok(actualizadoUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of all users.
     * 
     * @return A ResponseEntity with the list of UserModel objects if users are
     *         found, or a not found response if no users are found.
     */
    @GetMapping("/listar")
    @Operation(summary = "Obtener lista de Usuarios ", description = "Obtener lista de Usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    public ResponseEntity<List<UserModel>> listarUsers() {
        List<UserModel> user = userService.findAll();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing user.
     * 
     * @param id        The ID of the user to be updated.
     * @param userModel The updated UserModel object representing the user.
     * @return A ResponseEntity with the updated UserModel object if the user is
     *         updated successfully, or a not found response if the user is not
     *         found.
     */
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza un usuario existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<UserModel> actualizarUser(@PathVariable int id, @RequestBody UserModel userModel) {
        userModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        UserModel actualizarUser = userService.actualizarUser(id, userModel);
        if (actualizarUser != null) {
            return ResponseEntity.ok(actualizarUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Updates the password of a user based on their ID.
     * 
     * @param id       The ID of the user whose password needs to be updated.
     * @param password The new password for the user.
     * @return A ResponseEntity with the updated UserModel object if the password is
     *         updated successfully, or a not found response if the user is not
     *         found.
     */
    @PutMapping("/actualizar/clave/{id}/{password}")
    @Operation(summary = "Actualizar clave", description = "Actualiza la clave un usuario según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Clave actualizada exitosamente", content = @Content(schema = @Schema(implementation = UserModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UserModel> actualizarClave(@PathVariable int id, @PathVariable String password) {
        UserModel user = userService.passwordUpdate(id, password);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
