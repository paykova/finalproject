package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.models.service.OtherActivityServiceModel;

import java.util.List;

public interface OtherActivityService {

    OtherActivityServiceModel addOtherActivity (OtherActivityServiceModel activityServiceModel);

    List<OtherActivityServiceModel> findAllOtherActivities();

    OtherActivity findOtherActivityById(String id) throws NotFoundException;

    OtherActivityServiceModel editOtherActivity(String id, OtherActivityServiceModel otherActivityServiceModel);

    OtherActivityServiceModel deleteOtherActivity(String id);

}
