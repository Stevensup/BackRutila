package co.edu.unbosque.Rutila.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * This class represents an Email Service that is responsible for sending
 * emails.
 */
@Service
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    /**
     * Constructs an EmailService with the specified JavaMailSender and
     * ObjectMapper.
     *
     * @param javaMailSender The JavaMailSender used to send emails.
     * @param objectMapper   The ObjectMapper used to parse JSON.
     */
    public EmailService(JavaMailSender javaMailSender, ObjectMapper objectMapper) {
        this.javaMailSender = javaMailSender;
        this.objectMapper = objectMapper;
    }

    /**
     * Sends an email using the provided JSON data.
     *
     * @param json The JSON data containing the recipient, subject, and body of the
     *             email.
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
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
}
