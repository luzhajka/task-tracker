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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    final TaskRepository taskRepository;
    final TaskDtoEntityMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskDtoEntityMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public TaskDto getTaskById(String taskId) {
        Optional<TaskEntity> taskOptional = taskRepository.findById(UUID.fromString(taskId));
        if (taskOptional.isEmpty()) {
            throw new EntityNotFoundExceptions("Task by id = " + taskId + " not found");
        }
        TaskEntity taskEntity = taskOptional.get();
        return mapper.entityToDto(taskEntity);
    }

    @Transactional
    @Override
    public List<TaskDto> getTasksByParameter(TaskFilterRequestDto taskFilterRequestDto) {

        Long releaseId = taskFilterRequestDto.getReleaseId();
        Long authorId = taskFilterRequestDto.getAuthorId();
        Long executorId = taskFilterRequestDto.getExecutorId();
        String status = taskFilterRequestDto.getStatus();
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<TaskEntity> taskEntityList;
        Optional<List<TaskEntity>> optionalTaskList;


        if (releaseId == null && authorId == null && executorId == null && status == null) {
            taskEntityList = taskRepository.findAll();
        } else if (releaseId == null && authorId == null && executorId == null) {
            optionalTaskList = taskRepository.findAllByStatus(status);
            if (optionalTaskList.isEmpty()) {
                throw new EntityNotFoundExceptions("Tasks by this status = " + status + " not found");
            }
            taskEntityList = optionalTaskList.get();
        } else if (releaseId == null && authorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByExecutorId(executorId);
            if (optionalTaskList.isEmpty()) {
                throw new EntityNotFoundExceptions("Tasks by this executor = " + executorId + " not found");
            }
            taskEntityList = optionalTaskList.get();
        } else if (releaseId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByAuthorId(authorId);
            if (optionalTaskList.isEmpty()) {
                throw new EntityNotFoundExceptions("Tasks by this author = " + authorId + " not found");
            }
            taskEntityList = optionalTaskList.get();
        } else if (authorId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByRelease(releaseId);
            if (optionalTaskList.isEmpty()) {
                throw new EntityNotFoundExceptions("Tasks by this release = " + releaseId + " not found");
            }
            taskEntityList = optionalTaskList.get();
        } else {
            optionalTaskList = taskRepository.findAllByReleaseIdAndExecutorIdAndStatus(
                    releaseId,
                    executorId,
                    status);
            if (optionalTaskList.isEmpty()) {
                throw new EntityNotFoundExceptions("Tasks by this release = " + releaseId
                        + " executor = " + executorId
                        + " status = " + status
                        + " not found");
            }

            taskEntityList = optionalTaskList.get();
        }

        for (TaskEntity entity : taskEntityList) {
            TaskDto taskDto = mapper.entityToDto(entity);
            taskDtoList.add(taskDto);
        }

        return taskDtoList;
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

