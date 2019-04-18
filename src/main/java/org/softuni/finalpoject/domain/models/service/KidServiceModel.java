package org.softuni.finalpoject.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.mappings.IHaveCustomMappings;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class KidServiceModel extends BaseServiceModel{

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private User parent;

    public KidServiceModel() {
    }

    @NotNull(message = "Kid Name cannot be null")
    @NotEmpty
    @Length(min = 2, message = "Kid Name must be at least 2 characters long.")
    @Length(max = 20, message = "Kid Name must be maximum 20 characters long.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Kid Birth Date cannot be null")
    @NotEmpty
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