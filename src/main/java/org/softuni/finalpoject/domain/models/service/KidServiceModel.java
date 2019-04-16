package org.softuni.finalpoject.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.mappings.IHaveCustomMappings;

import java.time.LocalDate;
import java.util.List;

public class KidServiceModel{

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private User parent;

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

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }


}