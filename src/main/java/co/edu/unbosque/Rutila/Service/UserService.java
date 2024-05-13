package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.UserModel;
import co.edu.unbosque.Rutila.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean deleteUser( int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }
    public UserModel authenticationUser(String email,String password){
        UserModel user= userRepository.findByEmail(email);
        if(user!= null){
            if(user.getHash_password() !=null && user.getHash_password().equals(password)){
                return user ;
            }
        }
        return null;
    }



}
