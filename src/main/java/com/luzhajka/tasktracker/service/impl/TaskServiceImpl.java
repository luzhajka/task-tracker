package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundExceptions;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.TaskService;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskDtoEntityMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskDtoEntityMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public TaskDto getTaskById(String taskId) {
        return taskRepository.findById(UUID.fromString(taskId))
                .map(mapper::entityToDto)
                .orElseThrow(
                        () -> new EntityNotFoundExceptions(format("Task by id = %s not found", taskId))
                );
    }

    @Transactional
    @Override
    public List<TaskDto> getTasksByParameter(TaskFilterRequestDto taskFilterRequestDto) {
        Long releaseId = taskFilterRequestDto.getReleaseId();
        Long authorId = taskFilterRequestDto.getAuthorId();
        Long executorId = taskFilterRequestDto.getExecutorId();
        String status = taskFilterRequestDto.getStatus();

        List<TaskEntity> taskEntityList;
        Optional<List<TaskEntity>> optionalTaskList;


        if (releaseId == null && authorId == null && executorId == null && status == null) {
            taskEntityList = taskRepository.findAll();
        } else if (releaseId == null && authorId == null && executorId == null) {
            optionalTaskList = taskRepository.findAllByStatus(status);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundExceptions(format("Tasks by this status = %s not found", status))
            );

        } else if (releaseId == null && authorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByExecutorId(executorId);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundExceptions(format("Tasks by this executor = %s not found", executorId))
            );


        } else if (releaseId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByAuthorId(authorId);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundExceptions(format("Tasks by this author = %s not found", authorId))
            );

        } else if (authorId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByRelease(releaseId);
            taskEntityList = optionalTaskList.orElseThrow(() -> new EntityNotFoundExceptions(
                    format("Tasks by this release = %s not found", releaseId))
            );

        } else {
            optionalTaskList = taskRepository.findAllByReleaseIdAndExecutorIdAndStatus(releaseId, executorId, status);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundExceptions(
                            format("Tasks by this release = %s executor %s status = %s not found", releaseId, executorId, status)
                    )
            );
        }

        return taskEntityList.stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public UUID postTask(CreateTaskDto createTaskDTO) {
        return null;
    }

    @Transactional
    @Override
    public void editTask(String taskId, EditTaskRequestDto editTaskDto) {

    }

    @Transactional
    @Override
    public void deleteTask(UUID id) {

    }

}

