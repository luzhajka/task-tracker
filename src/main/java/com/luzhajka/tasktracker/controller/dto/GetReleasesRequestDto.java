package com.luzhajka.tasktracker.controller.dto;

public class GetReleasesRequestDto {
    Long projectID;

    public GetReleasesRequestDto(Long projectID) {
        this.projectID = projectID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }
}
