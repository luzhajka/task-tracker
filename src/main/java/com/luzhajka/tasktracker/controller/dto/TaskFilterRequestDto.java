package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TaskFilterRequestDto {
    @Schema(description = "ID релиза")
    private Long releaseId;

    @Schema(description = "ID автора проекта")
    private Long authorId;

    @Schema(description = "ID исполнителя проекта")
    private Long executorId;

    @Schema(description = "Статус проекта")
    private String status;

    public TaskFilterRequestDto(Long releaseId, Long authorId, Long executorId, String status) {
        this.releaseId = releaseId;
        this.authorId = authorId;
        this.executorId = executorId;
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

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
