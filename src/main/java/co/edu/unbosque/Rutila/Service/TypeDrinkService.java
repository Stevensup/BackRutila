package co.edu.unbosque.Rutila.Service;

import co.edu.unbosque.Rutila.Model.TypeDrinkModel;
import co.edu.unbosque.Rutila.Repository.TypeDrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
