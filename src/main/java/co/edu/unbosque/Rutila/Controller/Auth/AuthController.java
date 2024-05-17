package co.edu.unbosque.Rutila.Controller.Auth;


import co.edu.unbosque.Rutila.Controller.UserController;
import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.catalina.User;
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
     * @
     * @return ResponseEntity<String>
     */

    @PostMapping
    @Operation(summary = "Autenticar un cliente", description = "Autentica a un cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente autenticado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "412", description = "Error de precondición")
    })
    public ResponseEntity<String> autenticarCliente(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String userPassword = userLoginRequest.getUserPassword();

        UserModel userModel = userService.authenticationUser(email,userPassword);

        if (userModel != null ) {
            // Restablecer el contador de intentos fallidos si la autenticación es exitosa

            int clienteId = userModel.getId();
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\":\"Sesión iniciada\",\"correo\":\"" + email + "\",\"id\":" + clienteId + "}");


        } else {


                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña inválida");
            }
        }
    }



