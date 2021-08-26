package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectDto {

    @Schema(description = "ID проекта")
    Long id;

    @Schema(description = "Название проекта")
    String projectName;

    @Schema(description = "Заказчик проекта")
    String clientId;

    @Schema(description = "Статус проекта")
    ProjectStatus status;

    public ProjectDto(Long projectId, String projectName, String client, ProjectStatus status) {
        this.id = projectId;
        this.projectName = projectName;
        this.clientId = client;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

}
