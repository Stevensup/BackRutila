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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    UserController userController;





    @PostMapping("/autenticar")
    @Operation(summary = "Autenticar un usuario", description = "Autentica a un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "412", description = "Error de precondición")
    })
    public ResponseEntity<String> autenticarCliente(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String userPassword = userLoginRequest.getUserPassword();

        // Autenticar al usuario
        UserModel authenticatedUser = userService.authenticationUser(email, userPassword);

        if (authenticatedUser != null) {
            // Si el usuario se autentica exitosamente, devolver un código 200 con un mensaje de éxito
            return ResponseEntity.ok("Usuario autenticado exitosamente");
        } else {
            // Si las credenciales son inválidas, devolver un código 401 con un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

}
