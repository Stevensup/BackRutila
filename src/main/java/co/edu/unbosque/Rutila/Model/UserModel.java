package co.edu.unbosque.Rutila.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

/**
 * The UserModel class represents a user in the system.
 * It contains information such as the user's ID, name, email, phone number,
 * role ID, password hash,
 * creation timestamp, update timestamp, and deletion timestamp.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    private String phone;
    private int id_role;

    @Column(nullable = false)
    private String hash_password;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;
    @JsonIgnore
    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    /**
     * Returns a string representation of the UserModel object.
     *
     * @return a string representation of the UserModel object.
     */
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", id_role=" + id_role +
                ", hash_password='" + hash_password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
