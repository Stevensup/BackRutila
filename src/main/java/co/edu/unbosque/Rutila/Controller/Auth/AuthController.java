package co.edu.unbosque.Rutila.Controller.Auth;



import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UserService userService;



    /**
     * @return ResponseEntity<String>
     * @
     */

    @GetMapping
    @Operation(summary = "Autenticar un cliente", description = "Autentica a un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente autenticado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "412", description = "Error de precondición")
    })
    public ResponseEntity<String> autenticarCliente(@RequestParam String email, @RequestParam String userPassword) {
        try {
            // Validar la solicitud de inicio de sesión
            if (email == null || email.isEmpty() || userPassword == null || userPassword.isEmpty()) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                        .body("{\"message\":\"Campos de correo electrónico y contraseña son obligatorios\"}");
            }

            // Obtener el usuario por email
            UserModel userModel = userService.searchByemail(email);


            if (userModel != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

                String storedHashedPassword = userModel.getHash_password();
                if (passwordEncoder.matches(userPassword, storedHashedPassword)) {
                    // Restablecer el contador de intentos fallidos si la autenticación es exitosa
                    userService.resetFailedAttempts(email);

                    int clienteId = userModel.getId();
                    return ResponseEntity.status(HttpStatus.OK)
                            .body("{\"message\":\"Sesión iniciada\",\"correo\":\"" + email + "\",\"estado\":\"autenticado\"}");
                } else {
                    userService.incrementFailedAttempts(email);
                    if (userService.isAccountBlocked(email)) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("{\"message\":\"La cuenta está bloqueada\",\"estado\":\"bloqueado\"}");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("{\"message\":\"Usuario o contraseña inválida\",\"estado\":\"fallido\"}");
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("{\"message\":\"Usuario no encontrado\",\"estado\":\"fallido\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Ocurrió un error inesperado\"}");
        }
    }



}




