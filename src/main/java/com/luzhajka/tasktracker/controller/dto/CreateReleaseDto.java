package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;

public class CreateReleaseDto {
    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата старта релиза")
    private LocalDate startRelease;

    @Schema(description = "Дата окончания релиза")
    private LocalDate endRelease;

    @Schema(description = "Проект, которому принадлежит релиз")
    private Long projectId;

    public CreateReleaseDto(String version, LocalDate startRelease, LocalDate endRelease, Long projectId) {
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.projectId = projectId;
    }

    public static class CreateReleaseDtoBuilder {
        private String version;
        private LocalDate startRelease;
        private LocalDate endRelease;
        private Long projectId;


        public CreateReleaseDtoBuilder version(String version) {
            this.version = version;
            return this;
        }

        public CreateReleaseDtoBuilder startRelease(LocalDate startRelease) {
            this.startRelease = startRelease;
            return this;
        }

        public CreateReleaseDtoBuilder endRelease(LocalDate endRelease) {
            this.endRelease = endRelease;
            return this;
        }

        private CreateReleaseDtoBuilder project(Long projectId) {
            this.projectId = projectId;
            return this;
        }

        public CreateReleaseDto build() {
            return new CreateReleaseDto(version, startRelease, endRelease, projectId);
        }
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

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStartRelease(LocalDate startRelease) {
        this.startRelease = startRelease;
    }

    public void setEndRelease(LocalDate endRelease) {
        this.endRelease = endRelease;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
