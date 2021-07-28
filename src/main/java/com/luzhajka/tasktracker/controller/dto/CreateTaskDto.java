package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateTaskDto {

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Автор задачи")
    private UserDto author;

    @Schema(description = "Исполнитель задачи")
    private UserDto executor;

    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @Schema(description = "Релиз, на который объявлена задача")
    private Integer release;

    @Schema(description = "Проект, которому принадлежит задача")
    private Long project;

    public CreateTaskDto(String name, String description, UserDto author, UserDto executor, TaskStatus status, Integer release, Long project) {
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
        private UserDto author;
        private UserDto executor;
        private TaskStatus status;
        private Integer release;
        private Long project;


        public CreateTaskDto.CreateTaskDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder author(UserDto author) {
            this.author = author;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder executor(UserDto executor) {
            this.executor = executor;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public CreateTaskDto.CreateTaskDTOBuilder release(Integer release) {
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

    public UserDto getAuthor() {
        return author;
    }

    public UserDto getExecutor() {
        return executor;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Integer getRelease() {
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

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public void setExecutor(UserDto executor) {
        this.executor = executor;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setRelease(Integer release) {
        this.release = release;
    }

    public void setProject(Long project) {
        this.project = project;
    }

}
