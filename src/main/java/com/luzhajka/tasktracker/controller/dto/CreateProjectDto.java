package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateProjectDto {
    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private String client;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public CreateProjectDto(String projectName, String client, ProjectStatus status) {
        this.projectName = projectName;
        this.client = client;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

}
