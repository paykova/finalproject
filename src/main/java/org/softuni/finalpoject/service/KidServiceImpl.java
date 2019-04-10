package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.repository.KidRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidServiceImpl implements KidService{

    private final KidRepository kidRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public KidServiceImpl(KidRepository kidRepository, ModelMapper modelMapper, UserRepository userRepository, UserService userService) {
        this.kidRepository = kidRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userService = userService;
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
}
