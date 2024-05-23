package co.edu.unbosque.Rutila.Repository;

import co.edu.unbosque.Rutila.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents a repository for managing User entities.
 * It extends the JpaRepository interface, providing CRUD operations for User
 * entities.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    /**
     * Finds a user by name when the deletedAt field is null.
     * 
     * @param name The name of the user to find.
     * @return The user with the specified name, or null if not found.
     */
    UserModel findByNameAndDeletedAtIsNull(String name);

    /**
     * Finds a user by email when the deletedAt field is null.
     * 
     * @param email The email of the user to find.
     * @return The user with the specified email, or null if not found.
     */
    UserModel findByEmailAndDeletedAtIsNull(String email);

    /**
     * Finds all users when the deletedAt field is null.
     * 
     * @return A list of all users with the deletedAt field set to null.
     */
    List<UserModel> findAllByDeletedAtIsNull();
}
