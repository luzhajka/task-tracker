package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TaskDTO {
    @Schema(description = "ID задачи")
    private Long taskId;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Автор задачи")
    private UserDTO author;

    @Schema(description = "Исполнитель задачи")
    private UserDTO executor;

    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @Schema(description = "Релиз, на который объявлена задача")
    private Integer release;

    @Schema(description = "Проект, которому принадлежит задача")
    private ProjectDTO project;

    public TaskDTO(Long taskId, String name, String description, UserDTO author, UserDTO executor, TaskStatus status, Integer release, ProjectDTO project) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.executor = executor;
        this.status = status;
        this.release = release;
        this.project = project;
    }

    public static class TaskDTOBuilder {
        private Long taskId;
        private String name;
        private String description;
        private UserDTO author;
        private UserDTO executor;
        private TaskStatus status;
        private Integer release;
        private ProjectDTO project;

        public TaskDTOBuilder taskId(Long taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TaskDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskDTOBuilder author(UserDTO author) {
            this.author = author;
            return this;
        }

        public TaskDTOBuilder executor(UserDTO executor) {
            this.executor = executor;
            return this;
        }

        public TaskDTOBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskDTOBuilder release(Integer release) {
            this.release = release;
            return this;
        }

        public TaskDTOBuilder project(ProjectDTO project) {
            this.project = project;
            return this;
        }

        public TaskDTO build() {
            return new TaskDTO(taskId, name, description, author, executor, status, release, project);
        }
    }


    public Long getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public UserDTO getExecutor() {
        return executor;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Integer getRelease() {
        return release;
    }

    public ProjectDTO getProject() {
        return project;
    }


    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public void setExecutor(UserDTO executor) {
        this.executor = executor;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setRelease(Integer release) {
        this.release = release;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }


}
