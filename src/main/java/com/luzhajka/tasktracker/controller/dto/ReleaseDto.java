package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ReleaseDto {
    @Schema(description = "ID релиза")
    private Long releaseId;

    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата старта релиза")
    private LocalDate startRelease;

    @Schema(description = "Дата окончания релиза")
    private LocalDate endRelease;

    @Schema(description = "Проект, которому принадлежит релиз")
    private Long project;

    public ReleaseDto(Long releaseId, String version, LocalDate startRelease, LocalDate endRelease, Long project) {
        this.releaseId = releaseId;
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.project = project;
    }

    public static class ReleaseDtoBuilder {
        private Long releaseId;
        private String version;
        private LocalDate startRelease;
        private LocalDate endRelease;
        private Long project;

        public ReleaseDtoBuilder releaseId(Long releaseId) {
            this.releaseId = releaseId;
            return this;
        }

        public ReleaseDtoBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ReleaseDtoBuilder startRelease(LocalDate startRelease) {
            this.startRelease = startRelease;
            return this;
        }

        public ReleaseDtoBuilder endRelease(LocalDate endRelease) {
            this.endRelease = endRelease;
            return this;
        }

        private ReleaseDtoBuilder project(Long project) {
            this.project = project;
            return this;
        }

        public ReleaseDto build() {
            return new ReleaseDto(releaseId, version, startRelease, endRelease, project);
        }
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public String getVersion() {
        return version;
    }

    public LocalDate getStartRelease() {
        return startRelease;
    }

    public LocalDate getEndRelease() {
        return endRelease;
    }

    public Long getProject() {
        return project;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStartRelease(LocalDate startRelease) {
        this.startRelease = startRelease;
    }

    public void setEndRelease(LocalDate endRelease) {
        this.endRelease = endRelease;
    }

    public void setProject(Long project) {
        this.project = project;
    }
}