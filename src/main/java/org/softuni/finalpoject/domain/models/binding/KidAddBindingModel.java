package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class KidAddBindingModel {

    private String name;
    private LocalDate birthDate;
    private Gender gender;

    public KidAddBindingModel() {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
}
