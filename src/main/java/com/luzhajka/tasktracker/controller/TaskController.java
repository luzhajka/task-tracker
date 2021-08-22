package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import com.luzhajka.tasktracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "Управление")
@RestController("${server.api-base-url}")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Operation(summary = "получить задачу по ID")
    @GetMapping(value = "/task/{id}")
    public TaskDto getTask(@PathVariable("id") UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @Operation(summary = "получить все задачи по параметрам")
    @GetMapping(value = "/tasks")
    public List<TaskDto> getTasks(@RequestBody TaskFilterRequestDto taskFilterRequestDto) {
        return taskService.getTasksByParameter(taskFilterRequestDto);
    }


    @Operation(summary = "создать задачу")
    @PostMapping(value = "/tasks")
    public UUID postTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.postTask(createTaskDto);
    }

    @Operation(summary = "создать задачу с помощью CSV файла")
    @PostMapping(path = "/upload")
    public void uploadFile(@RequestParam MultipartFile file) {
        taskService.uploadTasks(file);
    }

    @Operation(summary = "изменить название и описание задачи")
    @PutMapping(value = "/task/{id}")
    public void editTask(@PathVariable("id") UUID taskId,
                         @RequestBody EditTaskRequestDto editTaskDto) {
        taskService.editTask(taskId, editTaskDto);
    }

    @Operation(summary = "назначить или изменить исполнителя задачи")
    @PutMapping(value = "/task/{id}/executor")
    public void editTaskExecutor(@PathVariable("id") UUID taskId,
                                 @RequestBody ChangeTaskExecutorDto executor) {
        taskService.changeExecutor(taskId, executor);
    }

    @Operation(summary = "изменить статус задачи")
    @PutMapping(value = "/task/{id}/status")
    public void editTaskStatus(@PathVariable("id") UUID taskId,
                               @RequestBody ChangeTaskStatusDto status) {
        taskService.changeStatus(taskId, status);
    }

    @Operation(summary = "назначить или изменить релиз для задачи")
    @PutMapping(value = "/task/{id}/release")
    public void editTaskRelease(@PathVariable("id") UUID taskId,
                                @RequestBody ChangeTaskReleaseDto release) {
        taskService.changeRelease(taskId, release);
    }


    //(назначение исполнителя, релиза и др, смена статуса задачи)


    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }

}
