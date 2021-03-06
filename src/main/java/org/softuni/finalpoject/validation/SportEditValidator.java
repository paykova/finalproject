package org.softuni.finalpoject.validation;

import org.softuni.finalpoject.domain.models.binding.SportAddBindingModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SportEditValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return SportAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SportAddBindingModel sportAddBindingModel = (SportAddBindingModel) o;

        if (sportAddBindingModel.getName() == null) {
            errors.rejectValue("name", "Sport Name cannot be Null!", "Sport Name cannot be Null!");
        }

        if (sportAddBindingModel.getName().equals("")) {
            errors.rejectValue("name", "Sport Name cannot be Empty!", "Sport Name cannot be Empty!");
        }
        if (sportAddBindingModel.getName().length() < 3) {
            errors.rejectValue("name", "Sport Name must contains at least 2 characters!", "Sport Name must contains at least 2 characters!");
        }
    }
}
