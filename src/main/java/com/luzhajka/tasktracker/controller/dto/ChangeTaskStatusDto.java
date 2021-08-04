package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangeTaskStatusDto {
    @Schema(description = "Статус задачи")
    private TaskStatus status;

    public ChangeTaskStatusDto() {
    }

    public ChangeTaskStatusDto(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
