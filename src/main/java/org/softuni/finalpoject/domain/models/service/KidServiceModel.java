package org.softuni.finalpoject.domain.models.service;

import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;

import java.time.LocalDate;

public class KidServiceModel extends BaseServiceModel {

    private String name;
    private LocalDate birthDate;
    private Gender gender;

    public KidServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
