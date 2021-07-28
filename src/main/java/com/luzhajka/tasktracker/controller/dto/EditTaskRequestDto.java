package com.luzhajka.tasktracker.controller.dto;

public class EditTaskRequestDto {
    String name;
    String description;
    String statusTask;
    Long executorID;
    Long releaseID;

    public EditTaskRequestDto(String name, String description, String statusTask, Long executorID, Long releaseID) {
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
        this.executorID = executorID;
        this.releaseID = releaseID;
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

    public Long getExecutorID() {
        return executorID;
    }

    public void setExecutorID(Long executorID) {
        this.executorID = executorID;
    }

    public Long getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(Long releaseID) {
        this.releaseID = releaseID;
    }
}
