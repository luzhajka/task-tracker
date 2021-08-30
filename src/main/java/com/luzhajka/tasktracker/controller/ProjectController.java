package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Управление")
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "получить проект по ID")
    @GetMapping(value = "/{id}")
    public ProjectDto getProject(@PathVariable("id") Long projectId) {
        return projectService.getProject(projectId);
    }


    @Operation(summary = "создать проект")
    @PostMapping(value = "/new")
    public Long createProject(@RequestBody CreateProjectDto createProjectDTO) {
        return projectService.createProject(createProjectDTO);
    }

    @Operation(summary = "изменить название проекта и заказчика")
    @PutMapping(value = "/{id}")
    public void editProject(@PathVariable("id") Long projectId,
                            @RequestBody EditProjectRequestDto editProjectRequestDto) {
        projectService.editProject(projectId, editProjectRequestDto);
    }

    @Operation(summary = "запустить проект")
    @PatchMapping(value = "/{id}/start")
    public void startProject(@PathVariable("id") Long projectId) {
        projectService.startProject(projectId);
    }

    @Operation(summary = "завершить проект")
    @PatchMapping(value = "/{id}/complete")
    public void completeProject(@PathVariable("id") Long projectId) {
        projectService.completeProject(projectId);
    }
}
