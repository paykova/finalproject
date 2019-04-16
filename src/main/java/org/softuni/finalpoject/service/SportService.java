package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.softuni.finalpoject.domain.entities.Sport;
import org.softuni.finalpoject.domain.models.service.SportServiceModel;

import java.util.List;

public interface SportService {

    SportServiceModel addSport(SportServiceModel sportServiceModel);

    List<SportServiceModel> findAllSports();

    SportServiceModel findSportById(String id);

    SportServiceModel editSport(String id, SportServiceModel sportServiceModel);

    SportServiceModel deleteSport(String id);
}
