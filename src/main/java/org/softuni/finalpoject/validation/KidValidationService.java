package org.softuni.finalpoject.validation;

import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;

public interface KidValidationService {

    boolean isValid(Kid kid);
    boolean isValid(KidServiceModel kidServiceModel);
}
