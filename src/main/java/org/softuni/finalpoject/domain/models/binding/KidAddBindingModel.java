package org.softuni.finalpoject.domain.models.binding;

import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;

import java.time.LocalDate;

public class KidAddBindingModel {

    private String name;
    private LocalDate birthDate;
    private Gender gender;

    public KidAddBindingModel() {
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
