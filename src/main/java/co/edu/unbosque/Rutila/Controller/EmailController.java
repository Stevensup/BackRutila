package co.edu.unbosque.Rutila.Controller;

import co.edu.unbosque.Rutila.Service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/correo")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Método para enviar un correo electrónico.
     *
     * @param json el contenido del correo electrónico en formato JSON
     * @return un mensaje indicando si el correo se envió exitosamente
     */
    @PostMapping("/enviar")
    @Operation(summary = "Enviar correo", description = "Envía un correo electrónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente"),
            @ApiResponse(responseCode = "412", description = "Precondición fallida")
    })
    public String enviarCorreo(@RequestBody String json) {
        emailService.enviarCorreo(json);
        return "Correo enviado exitosamente";
    }
}
