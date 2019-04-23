package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.constants.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserEditBindingModel {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
    private String email;

    public UserEditBindingModel() {
    }

    @NotNull(message = Constants.USER_NAME_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.USER_NAME_MUST_NOT_BE_EMPTY)
    @Length(min = 2, message = Constants.USER_NAME_MUST_BE_AT_LEAST_2_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.USER_NAME_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = Constants.OLD_PASSWORD_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.OLD_PASSWORD_MUST_NOT_BE_EMPTY)
    @Length(min = 6, message = Constants.OLD_PASSWORD_MUST_BE_AT_LEAST_6_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.OLD_PASSWORD_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotNull(message = Constants.PASSWORD_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.PASSWORD_MUST_NOT_BE_EMPTY)
    @Length(min = 6, message = Constants.PASSWORD_MUST_BE_AT_LEAST_6_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.PASSWORD_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message ="Confirm Password must not be null!")
    @NotEmpty(message = "Confirm Password must not be null!")
    @Length(min = 6, message = "Confirm Password must be at least 2 characters long!")
    @Length(max = 20, message = "Confirm Password must be maximum 20 characters long!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Email must not be null!")
    @NotEmpty(message = "Email must not be null!")
    @Length(min = 2, message = "Email must be at least 2 characters long!")
    @Length(max = 20, message = "Email must be maximum 20 characters long!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

