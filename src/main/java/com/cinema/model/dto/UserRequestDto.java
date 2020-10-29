package com.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class UserRequestDto {
    @NotNull(message = "The user must contain email!")
    private String email;
    @NotNull(message = "The user must contain password!")
    private String password;

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
}
