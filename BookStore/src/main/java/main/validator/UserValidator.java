package main.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import main.entities.User;
import main.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }
    
    
    public String validateUser(Object o) {
        User user = (User) o;
       
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            return "Invalid username length";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            return "Duplicate username!";
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            return "Invalid password length";
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
           return "The passwords are not the same";
        }
        
        if (user.getSalary()<0){
        	return "Invalid value for salary";
        }
        
        return "";
    }
}
