package org.softuni.finalpoject.domain.models.service;

import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.entities.Sport;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceModel extends BaseServiceModel {

    private List<LanguageServiceModel> languages;
    private List<SportServiceModel> sports;
//    private List<Instrument> instruments;
//    private List<OtherActivity> otherActivities;
    private List<KidServiceModel> kid;
    private InstrumentServiceModel instrument;

    public ProductServiceModel() {
    }

    public List<LanguageServiceModel> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageServiceModel> languages) {
        this.languages = languages;
    }

    public List<SportServiceModel> getSports() {
        return sports;
    }

    public void setSports(List<SportServiceModel> sports) {
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


    public List<KidServiceModel> getKid() {
        return kid;
    }

    public void setKid(List<KidServiceModel> kid) {
        this.kid = kid;
    }

    public InstrumentServiceModel getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentServiceModel instrument) {
        this.instrument = instrument;
    }
}
