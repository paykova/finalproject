package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.models.service.OtherActivityServiceModel;
import org.softuni.finalpoject.repository.OtherActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtherActivityServiceImpl implements OtherActivityService {

    private final OtherActivityRepository otherActivityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OtherActivityServiceImpl(OtherActivityRepository otherActivityRepository, ModelMapper modelMapper) {
        this.otherActivityRepository = otherActivityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OtherActivityServiceModel addOtherActivity(OtherActivityServiceModel otherActivityServiceModel) {
        OtherActivity otherActivity = this.modelMapper.map(otherActivityServiceModel, OtherActivity.class);
        return this.modelMapper.map(this.otherActivityRepository.saveAndFlush(otherActivity), OtherActivityServiceModel.class);
    }

    @Override
    public List<OtherActivityServiceModel> findAllOtherActivities() {
        return this.otherActivityRepository.findAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OtherActivityServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OtherActivity findOtherActivityById(String id) {
        OtherActivity otherActivity = this.otherActivityRepository.findById(id).orElse(null);
        if(otherActivity == null){
            throw  new IllegalArgumentException(id);
        }
        return otherActivity;
    }

    @Override
    public OtherActivityServiceModel editOtherActivity(String id, OtherActivityServiceModel otherActivityServiceModel) {
        OtherActivity otherActivity = this.otherActivityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        otherActivity.setName(otherActivityServiceModel.getName());
        return this.modelMapper.map(this.otherActivityRepository.saveAndFlush(otherActivity), OtherActivityServiceModel.class);
    }

    @Override
    public OtherActivityServiceModel deleteOtherActivity(String id) {
        OtherActivity otherActivity = this.otherActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.otherActivityRepository.delete(otherActivity);
        return this.modelMapper.map(otherActivity, OtherActivityServiceModel.class);
    }
}
