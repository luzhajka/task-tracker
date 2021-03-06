package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateTaskDto {

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Автор задачи")
    private Long author;

    @Schema(description = "Исполнитель задачи")
    private Long executor;

    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @Schema(description = "Релиз, на который объявлена задача")
    private Long release;

    @Schema(description = "Проект, которому принадлежит задача")
    private Long project;

    public CreateTaskDto(String name, String description, Long author, Long executor, TaskStatus status, Long release, Long project) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.executor = executor;
        this.status = status;
        this.release = release;
        this.project = project;
    }

    public static class CreateTaskDTOBuilder {
        private String name;
        private String description;
        private Long author;
        private Long executor;
        private TaskStatus status;
        private Long release;
        private Long project;


        public CreateTaskDto.CreateTaskDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder author(Long author) {
            this.author = author;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder executor(Long executor) {
            this.executor = executor;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder release(Long release) {
            this.release = release;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder project(Long project) {
            this.project = project;
            return this;
        }

        public CreateTaskDto build() {
            return new CreateTaskDto(name, description, author, executor, status, release, project);
        }
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getAuthor() {
        return author;
    }

    public Long getExecutor() {
        return executor;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getRelease() {
        return release;
    }

    public Long getProject() {
        return project;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setRelease(Long release) {
        this.release = release;
    }

    public void setProject(Long project) {
        this.project = project;
    }

}
