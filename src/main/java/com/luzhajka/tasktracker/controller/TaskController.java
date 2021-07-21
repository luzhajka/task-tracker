package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.luzhajka.tasktracker.controller.dto.TaskStatus.done;
import static com.luzhajka.tasktracker.controller.dto.TaskStatus.inProgress;

@Tag(name = "Управление задачами")
@RequestMapping("/api/taskManager")
@RestController

public class TaskController {


    @Operation(summary = "получить задачу по ID")
    @GetMapping(value = "/tasks/{taskId}")
    public TaskDTO getTask(@RequestHeader("requester") String requester,
                           @PathVariable("taskId") String taskId) {
        //заглушка. Вернуть задачу по taskId
        return new TaskDTO.TaskDTOBuilder()
                .taskId(1L)
                .name("first task")
                .release(123)
                .description("closed task")
                .author(new UserDTO(1L, "Ivan Ivanow", UserRole.client))
                .executor(new UserDTO(2L, "Vasya Ivanow", UserRole.developer))
                .project(new ProjectDTO(54L, "NORD", "Даша", ProjectStatus.IN_PROGRESS))
                .status(done)
                .build();
    }


    @Operation(summary = "получить все задачи по параметрам")
    @GetMapping(value = "/tasks")
    public List<TaskDTO> getTasks(@RequestHeader("requester") String requester,
                                  @RequestParam(name = "releaseID", required = false) Long releaseId,
                                  @RequestParam(name = "authorID", required = false) Long authorId,
                                  @RequestParam(name = "executorID", required = false) Long executorID,
                                  @RequestParam(name = "status", required = false) String status) {
        //заглушка. Фильтруем по параметрам if (параметр != null), если все null - возвращаем tasksList со всеми задачами
        List<TaskDTO> tasksList = List.of(
                new TaskDTO.TaskDTOBuilder()
                        .taskId(1L)
                        .name("first task")
                        .release(123)
                        .description("closed task")
                        .author(new UserDTO(1L, "Ivan Ivanow", UserRole.client))
                        .executor(new UserDTO(2L, "Vasya Ivanow", UserRole.projectOwner))
                        .project(new ProjectDTO(54L, "NORD", "Саша", ProjectStatus.IN_PROGRESS))
                        .status(done)
                        .build(),
                new TaskDTO.TaskDTOBuilder()
                        .taskId(2L)
                        .name("second task")
                        .release(123)
                        .description("task in progress")
                        .author(new UserDTO(2L, "Vasya Ivanow", UserRole.projectOwner))
                        .executor(new UserDTO(3L, "Peter Ivanow", UserRole.developer))
                        .project(new ProjectDTO(54L, "NORD", "Коля", ProjectStatus.IN_PROGRESS))
                        .status(inProgress)
                        .build()
        );
        return tasksList;
    }


    @Operation(summary = "создать задачу")
    @PostMapping(value = "/tasks")
    public Long postTask(@RequestHeader("requester") String requester,
                         @RequestBody CreateTaskDTO createTaskDTO) {
        //CreateTaskDTO в БД
        //БД присвоит taskID
        Long taskID = 23L;
        return taskID;
    }


    @Operation(summary = "изменить название и описание задачи")
    @PutMapping(value = "/task/{taskId}")
    public void editTask(@RequestHeader("requester") String requester,
                           @PathVariable("taskId") String taskId,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "description") String description) {
    }

    @Operation(summary = "изменить статус задачи")
    @PutMapping(value = "/task/{taskId}/status/{statusTask}")
    public void changeTaskStatus(@RequestHeader("requester") String requester,
                                 @PathVariable(name = "taskId") Long taskId,
                                 @PathVariable(name = "statusTask") String  statusTask) {
        //изменить статус задачи
    }

    @Operation(summary = "изменить исполнителя задачи")
    @PutMapping(value = "/task/{taskId}/executor/{executor}")
    public void changeTaskExecutor(@RequestHeader("requester") String requester,
                                   @PathVariable(name = "taskId") Long taskId,
                                   @PathVariable(name = "executor") Long executorID) {
        //изменить исполнителя задачи

    }

    @Operation(summary = "изменить или добавить релиз для задачи")
    @PutMapping(value = "/task/{taskId}/release/{release}")
    public void changeReleaseForTask(@RequestHeader("requester") String requester,
                                     @PathVariable(name = "taskId") Long taskId,
                                     @PathVariable(name = "release") Long releaseID) {
        //изменить или добавить релиз для задачи
    }


    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@RequestHeader("requester") String requester,
                           @PathVariable Long id) {
        // проверить роль запрашивающего на право удаления задачи
        // удаление сущности из БД
    }

}
