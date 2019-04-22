package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.models.service.OtherActivityServiceModel;
import org.softuni.finalpoject.domain.models.view.OtherActivityViewModel;
import org.softuni.finalpoject.repository.OtherActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtherActivityServiceImpl implements OtherActivityService {

    private final OtherActivityRepository otherActivityRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public OtherActivityServiceImpl(OtherActivityRepository otherActivityRepository,
                                    ModelMapper modelMapper,
                                    Validator validator) {
        this.otherActivityRepository = otherActivityRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public OtherActivityServiceModel addOtherActivity(OtherActivityServiceModel otherActivityServiceModel) {

        if(!validator.validate(otherActivityServiceModel).isEmpty()){
            throw new IllegalArgumentException(Constants.INVALID_ACTIVITY_ERROR_MESSAGE);
        }

        OtherActivity otherActivity = this.modelMapper.map(otherActivityServiceModel, OtherActivity.class);

        if (this.otherActivityRepository.findByName(otherActivity.getName()).orElse(null) != null) {
            throw new IllegalArgumentException(Constants.ACTIVITY_EXISTS_ERROR_MESSAGE);
        }

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
    public OtherActivityServiceModel findOtherActivityById(String id) {

        OtherActivity otherActivity = this.otherActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.ACTIVITY_NOT_FOUND_ERROR_MESSAGE));

        return this.modelMapper.map(otherActivity, OtherActivityServiceModel.class);
    }

    @Override
    public OtherActivityServiceModel editOtherActivity(String id, OtherActivityServiceModel otherActivityServiceModel) {

        OtherActivity otherActivity = this.otherActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.ACTIVITY_NOT_FOUND_ERROR_MESSAGE));

        if (this.otherActivityRepository.findByName(otherActivity.getName()).orElse(null) != null) {
            throw new IllegalArgumentException(Constants.ACTIVITY_EXISTS_ERROR_MESSAGE);
        }
        otherActivity.setName(otherActivityServiceModel.getName());

        return this.modelMapper.map(this.otherActivityRepository.saveAndFlush(otherActivity), OtherActivityServiceModel.class);
    }

    @Override
    public OtherActivityServiceModel deleteOtherActivity(String id) {

        OtherActivity otherActivity = this.otherActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.ACTIVITY_NOT_FOUND_ERROR_MESSAGE));

        this.otherActivityRepository.delete(otherActivity);

        return this.modelMapper.map(otherActivity, OtherActivityServiceModel.class);
    }

    @Override
    public List<OtherActivityViewModel> getOtherActivitiesNames() {
        List<OtherActivityViewModel> result;
        result = findAllOtherActivities().stream()
                .map(o -> this.modelMapper.map(o, OtherActivityViewModel.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<OtherActivityServiceModel> getOtherActivitiesByIds(List<String> ids) {

        List<OtherActivityServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            OtherActivityServiceModel model = findOtherActivityById(id);
            result.add(model);
        }
        return result;
    }
}
