package com.luzhajka.tasktracker.controller.dto;

public class TaskFilterRequestDto {

    Long releaseId;
    Long authorId;
    Long executorID;
    String status;

    public TaskFilterRequestDto(Long releaseId, Long authorId, Long executorID, String status) {
        this.releaseId = releaseId;
        this.authorId = authorId;
        this.executorID = executorID;
        this.status = status;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getExecutorID() {
        return executorID;
    }

    public void setExecutorID(Long executorID) {
        this.executorID = executorID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
