package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.repository.KidRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KidServiceImpl implements KidService{

    private final KidRepository kidRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Autowired
    public KidServiceImpl(KidRepository kidRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.kidRepository = kidRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public KidServiceModel addKid(KidServiceModel kidServiceModel, String name) {
        User user = userRepository.findByUsername(name).orElseThrow();
        Kid kid = this.modelMapper.map(kidServiceModel, Kid.class);
        kid.setParent(user);
        try {
            kid = this.kidRepository.saveAndFlush(kid);
            return this.modelMapper.map(kid, KidServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KidServiceModel> findAllKids() {
        var models = this.kidRepository.findAll()
                .stream()
                .map(u -> this.modelMapper
                        .map(u, KidServiceModel.class))
                .collect(Collectors.toList());
        return models;
    }

    @Override
    public List<KidServiceModel> findKidsByParent(String username) {
        var model = this.kidRepository.findAllKidsByParent_Id(username)
                .stream()
                .map(k -> modelMapper.map(k, KidServiceModel.class))
                .collect(Collectors.toList());
        return model;
    }
}
