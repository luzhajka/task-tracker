package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class TaskDto {
    @Schema(description = "ID задачи")
    private UUID taskId;

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
    private Integer release;

    @Schema(description = "Проект, которому принадлежит задача")
    private Long project;

    public TaskDto(UUID taskId, String name, String description, Long author, Long executor, TaskStatus status, Integer release, Long project) {
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
        private UUID taskId;
        private String name;
        private String description;
        private Long author;
        private Long executor;
        private TaskStatus status;
        private Integer release;
        private Long project;

        public TaskDTOBuilder taskId(UUID taskId) {
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

        public TaskDTOBuilder author(Long author) {
            this.author = author;
            return this;
        }

        public TaskDTOBuilder executor(Long executor) {
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

        public TaskDTOBuilder project(Long project) {
            this.project = project;
            return this;
        }

        public TaskDto build() {
            return new TaskDto(taskId, name, description, author, executor, status, release, project);
        }
    }


    public UUID getTaskId() {
        return taskId;
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

    public Integer getRelease() {
        return release;
    }

    public Long getProject() {
        return project;
    }


    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
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

    public void setRelease(Integer release) {
        this.release = release;
    }

    public void setProject(Long project) {
        this.project = project;
    }


}
