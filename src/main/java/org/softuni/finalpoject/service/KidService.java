package org.softuni.finalpoject.service;

import org.softuni.finalpoject.domain.models.service.KidServiceModel;

import java.util.List;

public interface KidService {

    KidServiceModel addKid(KidServiceModel kidServiceModel, String name);

    List<KidServiceModel> findAllKids();

    List<KidServiceModel> findKidsByParent(String username);


}
