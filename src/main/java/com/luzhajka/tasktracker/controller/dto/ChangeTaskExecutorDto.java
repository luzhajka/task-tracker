package com.luzhajka.tasktracker.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class ChangeTaskExecutorDto {

    @Schema(description = "Исполнитель задачи")
    private Long executor;

    public ChangeTaskExecutorDto() {
    }

    public ChangeTaskExecutorDto(Long executor) {
        this.executor = executor;
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }
}
