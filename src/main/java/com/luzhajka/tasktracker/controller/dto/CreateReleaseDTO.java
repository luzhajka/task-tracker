package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CreateReleaseDTO {
    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата старта релиза")
    private LocalDate startRelease;

    @Schema(description = "Дата окончания релиза")
    private LocalDate endRelease;

    @Schema(description = "Проект, которому принадлежит релиз")
    private ProjectDTO project;

    public CreateReleaseDTO(String version, LocalDate startRelease, LocalDate endRelease, ProjectDTO project) {
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.project = project;
    }

    public static class CreateReleaseDTOBuilder{
        private String version;
        private LocalDate startRelease;
        private LocalDate endRelease;
        private ProjectDTO project;


        public CreateReleaseDTO.CreateReleaseDTOBuilder version(String version){
            this.version = version;
            return this;
        }

        public CreateReleaseDTO.CreateReleaseDTOBuilder startRelease(LocalDate startRelease){
            this.startRelease = startRelease;
            return this;
        }

        public CreateReleaseDTO.CreateReleaseDTOBuilder endRelease(LocalDate endRelease){
            this.endRelease = endRelease;
            return this;
        }

        private CreateReleaseDTO.CreateReleaseDTOBuilder project(ProjectDTO project){
            this.project = project;
            return this;
        }

        public CreateReleaseDTO build (){
            return new CreateReleaseDTO (version, startRelease, endRelease, project);
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

    public ProjectDTO getProject() {
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

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

}
