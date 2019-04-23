package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class KidAddBindingModel {

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private MultipartFile image;
    private String description;
    private List<String> languages;
    private List<String> instruments;
    private List<String> sports;
    private List<String> otheractivities;

    public KidAddBindingModel() {
    }

    @NotNull(message = Constants.KID_NAME_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.KID_NAME_MUST_NOT_BE_EMPTY)
    @Length(min = 2, message = Constants.KID_NAME_MUST_BE_AT_LEAST_2_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.KID_NAME_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(pattern = Constants.LOCAL_DATE_FORMAT_PATTERN)
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotNull(message = Constants.DESCRIPTION_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.DESCRIPTION_MUST_NOT_BE_EMPTY)
    @Length(min = 5, message = Constants.DESCRIPTION_MUST_BE_AT_LEAST_5_CHARACTERS_LONG)
    @Length(max = 50, message = Constants.DESCRIPTION_MUST_BE_MAX_50_CHARACTERS_LONG)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(message = Constants.DESCRIPTION_MUST_NOT_BE_EMPTY)
    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }


    public List<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    public List<String> getOtheractivities() {
        return otheractivities;
    }

    public void setOtheractivities(List<String> otheractivities) {
        this.otheractivities = otheractivities;
    }
}
