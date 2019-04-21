package org.softuni.finalpoject.validation.implementations;

import org.softuni.finalpoject.domain.models.service.UserServiceModel;
import org.softuni.finalpoject.validation.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {

    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
