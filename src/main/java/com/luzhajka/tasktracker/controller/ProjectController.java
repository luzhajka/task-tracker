package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.controller.dto.ProjectStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Управление")
@RestController
public class ProjectController {

    @Operation(summary = "получить проект по ID")
    @GetMapping(value = "/project/{id}")
    public ProjectDto getProject(@PathVariable("id") String projectId) {
        //заглушка. Вернуть проект по projectId
        return new ProjectDto(13L, "проект1", "Mike", ProjectStatus.DONE);
    }


    @Operation(summary = "создать проект")
    @PostMapping(value = "/project")
    public java.lang.Long postProject(@RequestBody CreateProjectDto createProjectDTO) {
        //createProjectDTO в БД
        //БД присвоит projectID
        java.lang.Long projectID = 11L;
        return projectID;
    }

    @Operation(summary = "изменить название проекта и заказчика")
    @PutMapping(value = "/project/{id}")
    public void editProject(@PathVariable("id") String projectId,
                            @RequestBody EditProjectRequestDto editProjectRequestDto) {

    }
}
