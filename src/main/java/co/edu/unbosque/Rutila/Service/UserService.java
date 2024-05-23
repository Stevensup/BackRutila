package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The UserService class is responsible for handling user-related operations.
 * It provides methods for saving, updating, deleting, and searching for users
 * in the database.
 * It also includes functionality for user authentication and managing login
 * attempts.
 */
/**
 * The UserService class is responsible for managing user-related operations.
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    private Map<String, Integer> failedLoginAttempts = new HashMap<>();
    private Map<String, Boolean> blockedAccounts = new HashMap<>();
    private static final int MAX_FAILED_ATTEMPTS = 3;

    /**
     * Saves a user in the database.
     * 
     * @param user The user to be saved.
     * @return The saved user.
     */
    public UserModel saveUser(UserModel user) {
        logger.info("Entro al metodo de guardado");
        UserModel userModel = userRepository.save(user);
        logger.info("Se guardo exitosamente");
        return userModel;
    }

    /**
     * Updates a user in the database.
     * 
     * @param id   The ID of the user to be updated.
     * @param user The updated user.
     * @return The updated user if it exists, null otherwise.
     */
    public UserModel updateUser(int id, UserModel user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    /**
     * Performs a logical deletion of a user in the database.
     * 
     * @param id      The ID of the user to be deleted.
     * @param deleted The timestamp of the deletion.
     * @return The deleted user if it exists, null otherwise.
     */
    public UserModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<UserModel> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setDeletedAt(deleted);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    /**
     * Authenticates a user based on the provided email and password.
     * 
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The authenticated user if the email and password match, null
     *         otherwise.
     */
    public UserModel authenticationUser(String email, String password) {
        UserModel user = userRepository.findByEmailAndDeletedAtIsNull(email);
        if (user != null) {
            if (user.getHash_password() != null && user.getHash_password().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Updates the password of a user in the database.
     * 
     * @param id       The ID of the user to update the password.
     * @param password The new password.
     * @return The updated user if it exists, null otherwise.
     */
    public UserModel passwordUpdate(int id, String password) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setHash_password(password);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    /**
     * Searches for a user by name in the database.
     * 
     * @param name The name of the user to search for.
     * @return The user with the specified name if it exists, null otherwise.
     */
    public UserModel searchByname(String name) {
        return userRepository.findByNameAndDeletedAtIsNull(name);
    }

    /**
     * Searches for a user by email in the database.
     * 
     * @param email The email of the user to search for.
     * @return The user with the specified email if it exists, null otherwise.
     */
    public UserModel searchByemail(String email) {
        return userRepository.findByEmailAndDeletedAtIsNull(email);
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all users.
     */
    public List<UserModel> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    /**
     * Updates a user in the database.
     * 
     * @param id        The ID of the user to be updated.
     * @param userModel The updated user.
     * @return The updated user if it exists, null otherwise.
     */
    public UserModel actualizarUser(int id, UserModel userModel) {
        if (userRepository.existsById(id)) {
            userModel.setId(id);
            return userRepository.save(userModel);
        } else {
            return null;
        }
    }

    /**
     * Checks if an account is blocked.
     * 
     * @param email The email of the account to check.
     * @return true if the account is blocked, false otherwise.
     */
    public boolean isAccountBlocked(String email) {
        return blockedAccounts.getOrDefault(email, false);
    }

    /**
     * Increments the number of failed login attempts for an account.
     * If the number of failed attempts reaches the maximum allowed, the account is
     * blocked.
     * 
     * @param email The email of the account.
     */
    public void incrementFailedAttempts(String email) {
        int attempts = failedLoginAttempts.getOrDefault(email, 0) + 1;
        failedLoginAttempts.put(email, attempts);
        if (attempts >= MAX_FAILED_ATTEMPTS) {
            blockedAccounts.put(email, true);
        }
    }

    /**
     * Resets the number of failed login attempts for an account and unblocks it.
     * 
     * @param email The email of the account.
     */
    public void resetFailedAttempts(String email) {
        failedLoginAttempts.remove(email);
        blockedAccounts.remove(email);
    }
}
