package com.luzhajka.tasktracker.controller.dto;

public class EditTaskRequestDto {
    String name;
    String description;
    String statusTask;
    Long executorId;
    Long releaseId;

    public EditTaskRequestDto(String name, String description, String statusTask, Long executorId, Long releaseId) {
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
        this.executorId = executorId;
        this.releaseId = releaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }
}
