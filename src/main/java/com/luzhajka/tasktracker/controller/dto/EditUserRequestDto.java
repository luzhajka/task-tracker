package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EditUserRequestDto {
    @Schema(description = "Имя пользователя")
    private String userName;

    @Schema(description = "Роль пользователя")
    private String userRole;

    public EditUserRequestDto(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
