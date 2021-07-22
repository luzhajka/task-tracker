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
    private ProjectDto project;

    public CreateReleaseDto(String version, LocalDate startRelease, LocalDate endRelease, ProjectDto project) {
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.project = project;
    }

    public static class CreateReleaseDtoBuilder {
        private String version;
        private LocalDate startRelease;
        private LocalDate endRelease;
        private ProjectDto project;


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

        private CreateReleaseDtoBuilder project(ProjectDto project) {
            this.project = project;
            return this;
        }

        public CreateReleaseDto build() {
            return new CreateReleaseDto(version, startRelease, endRelease, project);
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

    public ProjectDto getProject() {
        return project;
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

    public void setProject(ProjectDto project) {
        this.project = project;
    }

}
