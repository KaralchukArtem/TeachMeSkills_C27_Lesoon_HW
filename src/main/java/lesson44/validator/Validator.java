package lesson44.validator;

import lesson44.model.ClientModel;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean isClientModelValid(ClientModel clientModel){
        if (clientModel != null){
            return true;
        }
        return false;
    }
}
