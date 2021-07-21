package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDTO;
import com.luzhajka.tasktracker.controller.dto.ProjectDTO;
import com.luzhajka.tasktracker.controller.dto.ProjectStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Управление проектами")
@RequestMapping("/api/projectManager")
@RestController
public class ProjectController {

    @Operation(summary = "получить проект по ID")
    @GetMapping(value = "/project/{projectId}")
    public ProjectDTO getProject(@RequestHeader("requester") String requester,
                                 @PathVariable("projectId") String projectId) {
        //заглушка. Вернуть проект по projectId
        return new ProjectDTO(13L, "проект1", "Mike", ProjectStatus.DONE);
    }

    @Operation(summary = "получить список проектов по заказчику или по статусу")
    @GetMapping(value = "/projects")
    public List<ProjectDTO> getProjects(@RequestHeader("requester") String requester,
                                        @RequestParam(name = "client-id", required = false) Long clientID,
                                        @RequestParam(name = "status", required = false) String status) {
        //заглушка. Фильтруем по параметрам if (параметр != null), если все null - возвращаем tasksList со всеми проектами
        List<ProjectDTO> projectList = List.of();
        return projectList;
    }


    @Operation(summary = "создать проект")
    @PostMapping(value = "/project")
    public Long postProject(@RequestHeader("requester") String requester,
                            @RequestBody CreateProjectDTO createProjectDTO) {
        //createProjectDTO в БД
        //БД присвоит projectID
        Long projectID = 11L;
        return projectID;
    }

    @Operation(summary = "изменить название проекта и заказчика")
    @PutMapping(value = "/project/{projectId}")
    public void editProject(@RequestHeader("requester") String requester,
                            @PathVariable("projectId") String projectId,
                            @RequestParam(name = "projectName") String projectName,
                            @RequestParam(name = "client") String clientName) {

    }

    @Operation(summary = "изменить статус проекта")
    @PutMapping(value = "/project/{projectId}/status/{statusProject}")
    public void changeProjectStatus(@RequestHeader("requester") String requester,
                                    @PathVariable(name = "projectId") Long projectId,
                                    @PathVariable(name = "statusProject") String statusProject) {
        //изменить статус проекта
    }
}
