package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.entities.*;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.repository.KidRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.softuni.finalpoject.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KidServiceImpl implements KidService {

    private final KidRepository kidRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;


    @Autowired
    public KidServiceImpl(KidRepository kidRepository,
                          ModelMapper modelMapper,
                          UserRepository userRepository,
                          ValidationUtil validationUtil) {
        this.kidRepository = kidRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public KidServiceModel findKidById(String id) {

        Kid kid = this.kidRepository.findById(id).orElse(null);

        if (kid == null) {
            throw new IllegalArgumentException(Constants.KID_NOT_FOUND_ERROR_MESSAGE);
        }

        return this.modelMapper.map(kid, KidServiceModel.class);
    }

    @Override
    public KidServiceModel addKid(KidServiceModel kidServiceModel, String name) {

        if (!this.validationUtil.isValid(kidServiceModel)) {
            throw new IllegalArgumentException(Constants.TRYING_TO_ADD_INVALID_DATA);
        }

        Kid kid = this.kidRepository.findByName(kidServiceModel.getName()).orElse(null);

        if (kid != null) {
            throw new IllegalArgumentException(Constants.KID_EXISTS_ERROR_MESSAGE);
        }

        kid = this.modelMapper.map(kidServiceModel, Kid.class);

        User user = userRepository.findByUsername(name).orElseThrow();

        kid.setParent(user);

        try {
            kid = this.kidRepository.saveAndFlush(kid);
            return this.modelMapper.map(kid, KidServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KidServiceModel> findAllKids() {

        return this.kidRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, KidServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<KidServiceModel> findKidsByParent(String username) {
        return this.kidRepository.findAllKidsByParent_Id(username)
                .stream()
                .map(k -> modelMapper.map(k, KidServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteKid(String id) {

        Kid kid = this.kidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.PROBLEM_ERROR_MESSAGE));

        this.kidRepository.delete(kid);
    }

    @Override
    public KidServiceModel editKid(String id, KidServiceModel kidServiceModel, String name) {

        Kid kid = this.kidRepository.findById(id)
                .orElse(null);

        String image = kid.getImageUrl();

        if (kid == null) {
            throw new IllegalArgumentException(Constants.KID_EXISTS_ERROR_MESSAGE);
        }

        kid = this.modelMapper.map(kidServiceModel, Kid.class);
        User user = userRepository.findByUsername(name).orElseThrow();
        kid.setParent(user);
        kid.setImageUrl(image);

        try {
            kid = this.kidRepository.saveAndFlush(kid);
            return this.modelMapper.map(kid, KidServiceModel.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(Constants.ALL_REQUIRED_INFO_ERROR_MESSAGE);
        }
        // return this.modelMapper.map(this.kidRepository.saveAndFlush(kid), KidServiceModel.class);
    }
}
