package org.softuni.finalpoject.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kid")
public class Kid extends BaseEntity {

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String imageUrl;
    private String description;
//    private List<Language> languages;
//    private List<Sport> sports;
//    private List<Instrument> instruments;
//    private List<OtherActivity> otherActivities;

    public Kid() {
    }

    @Column(name = "name", nullable = false, unique = false, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date", nullable = false, unique = false, updatable = true)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Enumerated
    @Column(name = "gender", nullable = false, unique = false, updatable = true)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//    public List<Language> getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(List<Language> languages) {
//        this.languages = languages;
//    }
//
//    public List<Sport> getSports() {
//        return sports;
//    }
//
//    public void setSports(List<Sport> sports) {
//        this.sports = sports;
//    }
//
//    public List<Instrument> getInstruments() {
//        return instruments;
//    }
//
//    public void setInstruments(List<Instrument> instruments) {
//        this.instruments = instruments;
//    }
//
//    public List<OtherActivity> getOtherActivities() {
//        return otherActivities;
//    }
//
//    public void setOtherActivities(List<OtherActivity> otherActivities) {
//        this.otherActivities = otherActivities;
//    }
}