package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ReleaseDto {
    @Schema(description = "ID релиза")
    private Long id;

    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата старта релиза")
    private LocalDate startRelease;

    @Schema(description = "Дата окончания релиза")
    private LocalDate endRelease;

    @Schema(description = "Проект, которому принадлежит релиз")
    private Long projectId;

    public ReleaseDto(Long id, String version, LocalDate startRelease, LocalDate endRelease, Long projectId) {
        this.id = id;
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.projectId = projectId;
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

    public Long getId() {
        return id;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
