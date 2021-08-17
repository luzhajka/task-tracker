package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserDto {

    @Schema(description = "Имя пользователя")
    private String name;

    @Schema(description = "Роль пользователя")
    private UserRole role;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Права пользователя")
    private PermissionRole permissionRole;


    public CreateUserDto(String name, UserRole role, String login, String password, PermissionRole permissionRole) {
        this.name = name;
        this.role = role;
        this.login = login;
        this.password = password;
        this.permissionRole = permissionRole;
    }

    public CreateUserDto() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissionRole getPermissionRole() {
        return permissionRole;
    }

    public void setPermissionRole(PermissionRole permissionRole) {
        this.permissionRole = permissionRole;
    }
}

