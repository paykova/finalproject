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
    public KidServiceModel findKidById(String id) {
        Kid kid = this.kidRepository.findById(id).orElse(null);
        if (kid == null) {
            throw new IllegalArgumentException(id);
        }
        return this.modelMapper.map(kid, KidServiceModel.class);

    }

    @Override
    public KidServiceModel addKid(KidServiceModel kidServiceModel, String name) {
        User user = userRepository.findByUsername(name).orElseThrow();
        Kid kid = this.modelMapper.map(kidServiceModel, Kid.class);

        try {
            kid = this.kidRepository.saveAndFlush(kid);
            return this.modelMapper.map(kid, KidServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public KidServiceModel addKidd(KidServiceModel kidServiceModel) {

      // Kid kid = this.kidRepository.findByName(KidServiceModel.getName()).orElse(null);

        Kid kid = new Kid();

        if (kid != null) {
            throw new IllegalArgumentException(new IllegalArgumentException());
        }

        kid = this.modelMapper.map(kidServiceModel, Kid.class);
        kid = this.kidRepository.save(kid);

        return this.modelMapper.map(kid, KidServiceModel.class);
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
        return null;
    }


}
