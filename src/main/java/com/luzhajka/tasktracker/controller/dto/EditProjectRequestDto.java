package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EditProjectRequestDto {

    @Schema (description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private Long clientId;

    @Schema(description = "Статус проекта")
    private String statusProject;

    public EditProjectRequestDto(String projectName, Long clientId, String statusProject) {
        this.projectName = projectName;
        this.clientId = clientId;
        this.statusProject = statusProject;
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

    public String getStatusProject() {
        return statusProject;
    }

    public void setStatusProject(String statusProject) {
        this.statusProject = statusProject;
    }
}
