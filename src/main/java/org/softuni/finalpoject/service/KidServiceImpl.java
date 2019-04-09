package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.repository.KidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidServiceImpl implements KidService{
    private final KidRepository kidRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public KidServiceImpl(KidRepository kidRepository, ModelMapper modelMapper) {
        this.kidRepository = kidRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public KidServiceModel addKid(KidServiceModel kidServiceModel) {
        Kid kid = this.modelMapper.map(kidServiceModel, Kid.class);
        return this.modelMapper.map(this.kidRepository.saveAndFlush(kid), KidServiceModel.class);
    }
}
