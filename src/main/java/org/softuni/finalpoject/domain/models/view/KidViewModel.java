package org.softuni.finalpoject.domain.models.view;


import java.time.LocalDate;

public class KidViewModel {

    private String id;
    private String name;
    private LocalDate birthDate;

    public KidViewModel() {
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
}
