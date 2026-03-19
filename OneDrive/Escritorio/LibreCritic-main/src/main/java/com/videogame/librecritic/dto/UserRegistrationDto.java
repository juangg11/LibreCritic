package com.videogame.librecritic.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserRegistrationDto {

    @NotEmpty(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
