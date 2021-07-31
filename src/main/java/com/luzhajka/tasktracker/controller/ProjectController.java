package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Управление")
@RestController("${server.api-base-url}")
public class ProjectController {

    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "получить проект по ID")
    @GetMapping(value = "/project/{id}")
    public ProjectDto getProject(@PathVariable("id") Long projectId) {
        return projectService.getProject(projectId);
    }


    @Operation(summary = "создать проект")
    @PostMapping(value = "/project")
    public Long postProject(@RequestBody CreateProjectDto createProjectDTO) {

        return null;
    }

    @Operation(summary = "изменить название проекта и заказчика")
    @PutMapping(value = "/project/{id}")
    public void editProject(@PathVariable("id") Long projectId,
                            @RequestBody EditProjectRequestDto editProjectRequestDto) {

    }
}
