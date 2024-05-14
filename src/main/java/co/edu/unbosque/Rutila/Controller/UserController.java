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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/guardarUser")
    @Operation(summary = "Agregar Usuarios", description = "Agrega el objeto users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el usuarios")
    })
    public ResponseEntity<String> guardarUsuario(@RequestBody UserModel user) {
        try {
            ;
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userService.saveUser(user);
            return ResponseEntity.ok("Usuario guardado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se insertó el Usuario" + user);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el usuario " + e.getMessage());
        }
    }

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

        actualizadoUser= userService.eliminadoLogico(id, deletedTimestamp);

        if (actualizadoUser != null) {
            return ResponseEntity.ok(actualizadoUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}