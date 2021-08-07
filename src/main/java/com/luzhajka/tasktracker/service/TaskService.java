package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    public TaskDto getTaskById(UUID taskId);

    public List<TaskDto> getTasksByParameter(TaskFilterRequestDto taskFilterRequestDto);

    public UUID postTask(CreateTaskDto createTaskDTO);

    public void editTask(UUID taskId, EditTaskRequestDto editTaskDto);

    public void deleteTask(UUID id);

    public void changeExecutor(UUID taskId, ChangeTaskExecutorDto changeTaskExecutorDto);

    public void changeStatus(UUID taskId, ChangeTaskStatusDto changeTaskStatusDto);

    public void changeRelease(UUID taskId, ChangeTaskReleaseDto changeTaskReleaseDto);
}

