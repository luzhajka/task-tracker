package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto {

    @Schema(description = "ID пользователя")
    Long userId;

    @Schema(description = "Имя пользователя")
    String name;

    @Schema(description = "Роль пользователя")
    UserRole role;

    public UserDto(Long userId, String name, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

