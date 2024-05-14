package co.edu.unbosque.Rutila.Controller.Auth;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
public class UserLoginRequest {
    private String email;
    private String userPassword;

    /**
     * @return String
     */
    // Getters y setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
