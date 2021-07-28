package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    public TaskDto getTaskById(String taskId);

    public List<TaskDto> getTasksByParameter(TaskFilterRequestDto taskFilterRequestDto);

    public UUID postTask(CreateTaskDto createTaskDTO);

    public void editTask(String taskId, EditTaskRequestDto editTaskDto);

    public void deleteTask(UUID id);
}
