package com.luzhajka.tasktracker.controller.dto;

public class EditReleaseRequestDto {
    String releaseVersion;
    String startDate;
    String endDate;

    public EditReleaseRequestDto(String releaseVersion, String startDate, String endDate) {
        this.releaseVersion = releaseVersion;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
