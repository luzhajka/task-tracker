package com.luzhajka.tasktracker.controller;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Управление")
@RestController("${server.api-base-url}")
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Operation(summary = "получить задачу по ID")
    @GetMapping(value = "/task/{id}")
    public TaskDto getTask(@PathVariable("id") String taskId) {
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
        //CreateTaskDTO в БД
        //БД присвоит taskID

        return UUID.randomUUID();
    }


    @Operation(summary = "изменить название и описание задачи")
    @PutMapping(value = "/task/{id}")
    public void editTask(@PathVariable("id") UUID taskId,
                         @RequestBody EditTaskRequestDto editTaskDto) {

    }


    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@PathVariable UUID id) {
        // проверить роль запрашивающего на право удаления задачи
        // удаление сущности из БД
    }

}
