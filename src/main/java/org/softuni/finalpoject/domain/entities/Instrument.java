package org.softuni.finalpoject.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "instruments")
public class Instrument extends BaseEntity {

    private String name;

    public Instrument() {
    }

    @Column(name = "instrument", unique = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
