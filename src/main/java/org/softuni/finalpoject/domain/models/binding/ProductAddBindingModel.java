package org.softuni.finalpoject.domain.models.binding;

import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.entities.Sport;

import java.math.BigDecimal;
import java.util.List;

public class ProductAddBindingModel {

    private BigDecimal price;
    private List<Language> languages;
    private List<Sport> sports;
    private List<Instrument> instruments;
    private List<OtherActivity> otherActivities;

    public ProductAddBindingModel() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
