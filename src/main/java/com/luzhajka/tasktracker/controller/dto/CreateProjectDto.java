package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateProjectDto {
    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private Long clientId;

    @Schema(description = "Статус проекта")
    private ProjectStatus status = ProjectStatus.WAITING;

    public CreateProjectDto(String projectName, Long clientId) {
        this.projectName = projectName;
        this.clientId = clientId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ProjectStatus getStatus() {
        return status;
    }

}
