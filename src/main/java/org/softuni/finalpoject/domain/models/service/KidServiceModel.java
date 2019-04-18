package org.softuni.finalpoject.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.Kid;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.mappings.IHaveCustomMappings;
import org.softuni.finalpoject.service.SportService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class KidServiceModel extends BaseServiceModel{

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String imageUrl;
    private String description;
    private LanguageServiceModel language;
    private InstrumentServiceModel instrument;
    private SportServiceModel sport;
    private OtherActivityServiceModel otheractivity;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Description Name cannot be null")
    @NotEmpty
    @Length(min = 2, message = "Description must be at least 2 characters long.")
    @Length(max = 100, message = "Description must be maximum 100 characters long.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LanguageServiceModel getLanguage() {
        return language;
    }

    public void setLanguage(LanguageServiceModel language) {
        this.language = language;
    }

    public InstrumentServiceModel getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentServiceModel instrument) {
        this.instrument = instrument;
    }

    public SportServiceModel getSport() {
        return sport;
    }

    public void setSport(SportServiceModel sport) {
        this.sport = sport;
    }

    public OtherActivityServiceModel getOtheractivity() {
        return otheractivity;
    }

    public void setOtheractivity(OtherActivityServiceModel otheractivity) {
        this.otheractivity = otheractivity;
    }
}