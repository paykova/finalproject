package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.constants.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InstrumentAddBindingModel {

    private String name;

    public InstrumentAddBindingModel() {
    }

    @NotNull(message = Constants.INSTRUMENT_NAME_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.INSTRUMENT_NAME_MUST_NOT_BE_EMPTY)
    @Length(min = 2, message = Constants.INSTRUMENT_NAME_MUST_BE_AT_LEAST_2_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.INSTRUMENT_NAME_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
