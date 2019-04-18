package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;

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

    @NotNull(message = "User Name cannot be null")
    @NotEmpty
    @Length(min = 2, message = "User Name must be at least 2 characters long.")
    @Length(max = 20, message = "User Name must be maximum 20 characters long.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Old Password cannot be null")
    @NotEmpty
    @Length(min = 6, message = "Old Password must be at least 2 characters long.")
    @Length(max = 20, message = "Old Password must be maximum 20 characters long.")
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotNull(message = "Edit Password cannot be null")
    @NotEmpty
    @Length(min = 6, message = "Password must be at least 2 characters long.")
    @Length(max = 20, message = "Password must be maximum 20 characters long.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Confirm Password cannot be null")
    @NotEmpty
    @Length(min = 6, message = "Confirm Password must be at least 2 characters long.")
    @Length(max = 20, message = "Confirm Password must be maximum 20 characters long.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Email cannot be null")
    @NotEmpty
    @Length(min = 2, message = "Email must be at least 2 characters long.")
    @Length(max = 20, message = "Email must be maximum 20 characters long.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

