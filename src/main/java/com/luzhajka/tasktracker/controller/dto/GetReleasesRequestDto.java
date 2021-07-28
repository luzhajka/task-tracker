package com.luzhajka.tasktracker.controller.dto;

public class GetReleasesRequestDto {
    Long projectId;

    public GetReleasesRequestDto(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
