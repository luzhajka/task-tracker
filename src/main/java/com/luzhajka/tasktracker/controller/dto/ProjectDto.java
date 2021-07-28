package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectDto {

    @Schema(description = "ID проекта")
    Long projectId;

    @Schema(description = "Название проекта")
    String projectName;

    @Schema(description = "Заказчик проекта")
    String client;

    @Schema(description = "Статус проекта")
    ProjectStatus status;

    public ProjectDto(Long projectId, String projectName, String client, ProjectStatus status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.client = client;
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
