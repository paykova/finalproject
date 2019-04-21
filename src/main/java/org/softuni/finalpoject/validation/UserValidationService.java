package org.softuni.finalpoject.validation;

import org.softuni.finalpoject.domain.models.service.UserServiceModel;

public interface UserValidationService {

    boolean isValid(UserServiceModel user);
}
