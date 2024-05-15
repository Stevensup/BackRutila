package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.DrinkModel;
import co.edu.unbosque.Rutila.Model.OrderModel;
import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import co.edu.unbosque.Rutila.Repository.TypeDrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TypeDrinkService {
    private final Logger logger = LoggerFactory.getLogger(TypeDrinkService.class);

    @Autowired
    private TypeDrinkRepository typeDrinkRepository;

    @Transactional
    public TypeDrinkModel saveTypeDrink (TypeDrinkModel typeDrink){
        logger.info("entro al metodo de guardado");
        TypeDrinkModel typeDrinkModel= typeDrinkRepository.save(typeDrink);
        logger.info("Se guardo con exito");
        return  typeDrinkModel;
    }


    public TypeDrinkModel searchByType (String type){

        return typeDrinkRepository.findByTypes(type);
    }


    public TypeDrinkModel eliminadoLogico(int id, Timestamp deleted) {
        Optional<TypeDrinkModel> optionalType = typeDrinkRepository.findById(id);

        if (optionalType.isPresent()) {
            TypeDrinkModel existingType = optionalType.get();
            existingType.setDeletedAt(deleted);
            return typeDrinkRepository.save(existingType);
        } else {
            return null;
        }
    }

    public List<TypeDrinkModel> findAll(){
        return typeDrinkRepository.findAllByDeletedAtIsNull();
    }

}
