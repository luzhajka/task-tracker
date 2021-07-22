package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.luzhajka.tasktracker.controller.dto.TaskStatus.done;
import static com.luzhajka.tasktracker.controller.dto.TaskStatus.inProgress;

@Tag(name = "Управление")
@RestController

public class TaskController {


    @Operation(summary = "получить задачу по ID")
    @GetMapping(value = "/task/{id}")
    public TaskDto getTask(@PathVariable("id") String taskId) {
        //заглушка. Вернуть задачу по taskId
        return new TaskDto.TaskDTOBuilder()
                .taskId(UUID.randomUUID())
                .name("first task")
                .release(123)
                .description("closed task")
                .author(12133L)
                .executor(12833L)
                .project(12303L)
                .status(done)
                .build();
    }


    @Operation(summary = "получить все задачи по параметрам")
    @GetMapping(value = "/tasks")
    public List<TaskDto> getTasks(@RequestBody TaskFilterRequestDto taskFilterRequestDto) {
        //заглушка. Фильтруем по параметрам if (параметр != null), если все null - возвращаем tasksList со всеми задачами
        List<TaskDto> tasksList = List.of(
                new TaskDto.TaskDTOBuilder()
                        .taskId(UUID.randomUUID())
                        .name("first task")
                        .release(123)
                        .description("closed task")
                        .author(1233L)
                        .executor(12337L)
                        .project(12334L)
                        .status(done)
                        .build(),
                new TaskDto.TaskDTOBuilder()
                        .taskId(UUID.randomUUID())
                        .name("second task")
                        .release(123)
                        .description("task in progress")
                        .author(12323L)
                        .executor(12133L)
                        .project(12633L)
                        .status(inProgress)
                        .build()
        );
        return tasksList;
    }


    @Operation(summary = "создать задачу")
    @PostMapping(value = "/tasks")
    public UUID postTask(@RequestBody CreateTaskDto createTaskDTO) {
        //CreateTaskDTO в БД
        //БД присвоит taskID

        return UUID.randomUUID();
    }


    @Operation(summary = "изменить название и описание задачи")
    @PutMapping(value = "/task/{id}")
    public void editTask(@PathVariable("id") String taskId,
                         @RequestBody EditTaskRequestDto editTaskDto) {

    }


    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/{id}")
    public void deleteTask(@PathVariable UUID id) {
        // проверить роль запрашивающего на право удаления задачи
        // удаление сущности из БД
    }

}