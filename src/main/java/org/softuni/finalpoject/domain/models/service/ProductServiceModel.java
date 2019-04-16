package org.softuni.finalpoject.domain.models.service;

import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.entities.Sport;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceModel extends BaseServiceModel {

    private List<Language> languages;
    private List<Sport> sports;
    private List<Instrument> instruments;
    private List<OtherActivity> otherActivities;

    public ProductServiceModel() {
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<OtherActivity> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(List<OtherActivity> otherActivities) {
        this.otherActivities = otherActivities;
    }
}
