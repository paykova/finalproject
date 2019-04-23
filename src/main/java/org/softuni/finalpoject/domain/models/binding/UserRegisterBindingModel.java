package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.constants.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
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

    @NotNull(message = Constants.CONFIRM_PASSWORD_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.CONFIRM_PASSWORD_MUST_NOT_BE_EMPTY)
    @Length(min = 6, message = Constants.CONFIRM_PASSWORD_MUST_BE_AT_LEAST_6_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.CONFIRM_PASSWORD_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = Constants.EMAIL_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.EMAIL_MUST_NOT_BE_EMPTY)
    @Length(min = 6, message = Constants.EMAIL_MUST_BE_AT_LEAST_6_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.EMAIL_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
