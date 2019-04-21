package org.softuni.finalpoject.validation.implementations;

import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.validation.KidValidationService;
import org.springframework.stereotype.Component;

@Component
public class KidValidationServiceImpl implements KidValidationService {
    @Override
    public boolean isValid(Kid kid) {
        return kid != null;
    }

    @Override
    public boolean isValid(KidServiceModel kidServiceModel) {
        return kidServiceModel != null;
    }
}

