package com.luzhajka.tasktracker.controller.dto;

public class EditProjectRequestDto {

    String projectName;
    String clientName;
    String statusProject;

    public EditProjectRequestDto(String projectName, String clientName, String statusProject) {
        this.projectName = projectName;
        this.clientName = clientName;
        this.statusProject = statusProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStatusProject() {
        return statusProject;
    }

    public void setStatusProject(String statusProject) {
        this.statusProject = statusProject;
    }
}
