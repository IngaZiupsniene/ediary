package demo.util;

import demo.model.User;
import demo.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
@Autowired
    IUserService iUserService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object ouser, Errors errors) { //tikriname useri, jei neatitinka - turesime error
        User user1=(User) ouser;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "EmptySpace"); //jei nors viename lauke "username" bus tuscias - ismes pranesima - error
      //jei egzistuoja toks vartotojas:
        if(iUserService.findByUserName(user1.getUsername())!=null){
            errors.rejectValue("username", "Warning.dublicate.username");
        }
        //kai username ilgis turi buti nuo 6 iki 32 simboliu
        if(user1.getUsername().length()<=6 || user1.getUsername().length()>=32){
            errors.rejectValue("username", "Warning.size.username");
        }
//tas pats su password:
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "EmptySpace");

        if(user1.getPassword().length()<=6 || user1.getPassword().length()>=32){
            errors.rejectValue("password", "Warning.size.password");
        }

        if (!user1.getPasswordconfirm().equals(user1.getPassword())){
            errors.rejectValue("passwordconfirm", "Warning.diff.passwordconfirm");
        }
    }
}
