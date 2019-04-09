package org.softuni.finalpoject.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kid")
public class Kid extends BaseEntity {

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private User parent;

    public Kid() {
    }

    @Column(name = "name",nullable = false, unique = false, updatable = true)
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

    @OneToOne(targetEntity = User.class)
    @JoinColumn(
            name = "parent_id",
            referencedColumnName = "id",
            nullable = false
    )
    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }
}