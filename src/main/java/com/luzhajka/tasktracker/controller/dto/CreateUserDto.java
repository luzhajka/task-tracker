package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserDto {

    @Schema(description = "Имя пользователя")
    private String name;

    @Schema(description = "Роль пользователя")
    private UserRole role;

    public CreateUserDto(String name, UserRole role) {

        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

