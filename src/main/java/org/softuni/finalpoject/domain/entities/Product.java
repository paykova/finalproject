package org.softuni.finalpoject.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private List<Language> languages;
    private List<Sport> sports;
    private List<Instrument> instruments;
    private List<OtherActivity> otherActivities;
    private Kid kid;

    public Product() {
    }

    @ManyToMany(targetEntity = Language.class)
    @JoinTable(
            name = "products_languages",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "language_id",
                    referencedColumnName = "id"
            )
    )
    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @ManyToMany(targetEntity = Sport.class)
    @JoinTable(
            name = "products_sports",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "sport_id",
                    referencedColumnName = "id"
            )
    )
    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @ManyToMany(targetEntity = Instrument.class)
    @JoinTable(
            name = "products_instruments",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "instrument_id",
                    referencedColumnName = "id"
            )
    )
    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    @ManyToMany(targetEntity = OtherActivity.class)
    @JoinTable(
            name = "products_otheractivities",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "otheractivity_id",
                    referencedColumnName = "id"
            )
    )
    public List<OtherActivity> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(List<OtherActivity> otherActivities) {
        this.otherActivities = otherActivities;
    }

    @ManyToOne(targetEntity = Kid.class)
    @JoinColumn(
            name = "kid_id",
            referencedColumnName = "id",
            nullable = false
    )
    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
