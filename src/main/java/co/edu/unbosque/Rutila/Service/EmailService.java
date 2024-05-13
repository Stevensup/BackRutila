package co.edu.unbosque.Rutila.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    /**
     * @param javaMailSender
     * @param objectMapper
     * @return
     */
    public EmailService(JavaMailSender javaMailSender, ObjectMapper objectMapper) {
        this.javaMailSender = javaMailSender;
        this.objectMapper = objectMapper;
    }

    /**
     * @param json
     */
    public void enviarCorreo(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);

            String destinatario = jsonNode.get("destinatario").asText();
            String asunto = jsonNode.get("asunto").asText();
            String cuerpo = jsonNode.get("cuerpo").asText();

            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destinatario);
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            javaMailSender.send(mensaje);
        } catch (Exception e) {
            // Manejar excepciones de manera adecuada
            e.printStackTrace();
        }
    }
}
