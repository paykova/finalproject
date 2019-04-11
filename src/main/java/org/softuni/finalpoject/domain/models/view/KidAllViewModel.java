package org.softuni.finalpoject.domain.models.view;

import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;

import java.time.LocalDate;

public class KidAllViewModel {

    private String id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private User parent;

    public KidAllViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
