package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.BarModel;
import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
private UserRepository userRepository;


    public UserModel saveUser(UserModel user){
        logger.info("Entro al metodo de guardado");
        UserModel userModel= userRepository.save(user);
        logger.info("Se guardo exitosamente");
        return userModel;
    }

    public UserModel updateUser(int id ,UserModel user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        }else{
            return null;
        }


    }
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
    public UserModel authenticationUser(String email,String password){
        UserModel user= userRepository.findByEmailAndDeletedAtIsNull(email);
        if(user!= null){
            if(user.getHash_password() !=null && user.getHash_password().equals(password)){
                return user ;
            }
        }
        return null;
    }


    public UserModel searchByname(String name){

        return userRepository.findByNameAndDeletedAtIsNull(name);
    }

    public UserModel searchByemail(String email){

        return userRepository.findByEmailAndDeletedAtIsNull(email);
    }

    public List<UserModel>  findAll(){
        return userRepository.findAllByDeletedAtIsNull();
    }

}
