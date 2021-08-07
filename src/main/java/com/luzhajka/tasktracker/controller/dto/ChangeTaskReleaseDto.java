package com.luzhajka.tasktracker.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangeTaskReleaseDto {
    @Schema(description = "Релиз, на который объявлена задача")
    private Long release;

    public ChangeTaskReleaseDto() {
    }

    public ChangeTaskReleaseDto(Long release) {
        this.release = release;
    }

    public Long getRelease() {
        return release;
    }

    public void setRelease(Long release) {
        this.release = release;
    }
}
