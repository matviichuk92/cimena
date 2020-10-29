package com.cinema.model.dto;

import com.cinema.validation.EmailConstraint;
import com.cinema.validation.PasswordConstraint;

@PasswordConstraint
public class UserRequestDto {
    @EmailConstraint
    private String email;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
