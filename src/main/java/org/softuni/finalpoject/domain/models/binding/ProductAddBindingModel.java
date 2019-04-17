package org.softuni.finalpoject.domain.models.binding;

import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.entities.Sport;

import java.math.BigDecimal;
import java.util.List;

public class ProductAddBindingModel {

    private List<String> languages;
    private List<String> sports;
//    private List<Instrument> instruments;
//    private List<OtherActivity> otherActivities;
    private List<String> kids;
    private String instrument;

    public ProductAddBindingModel() {
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

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


    public List<String> getKids() {
        return kids;
    }

    public void setKids(List<String> kids) {
        this.kids = kids;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
