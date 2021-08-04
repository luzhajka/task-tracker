package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class EditReleaseRequestDto {
    @Schema(description = "Версия релиза")
    private String releaseVersion;

    @Schema(description = "Дата старта релиза")
    private LocalDate startDate;

    @Schema(description = "Дата окончания релиза")
    private LocalDate endDate;

    public EditReleaseRequestDto(String releaseVersion, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
