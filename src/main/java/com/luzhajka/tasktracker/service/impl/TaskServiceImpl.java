package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EmptyRequestException;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.TaskService;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;
    private final TaskDtoEntityMapper mapper;
    private final CreateTaskDtoEntityMapper createMapper;


    public TaskServiceImpl(TaskRepository taskRepository, TaskDtoEntityMapper mapper, CreateTaskDtoEntityMapper createMapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.createMapper = createMapper;
    }

    @Transactional
    @Override
    public TaskDto getTaskById(UUID taskId) {
        return taskRepository.findById(taskId)
                .map(mapper::entityToDto)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("Task by ID = %s not found", taskId))
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
                    () -> new EntityNotFoundException(format("Tasks by this status = %s not found", status))
            );

        } else if (releaseId == null && authorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByExecutorId(executorId);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundException(format("Tasks by this executor by ID = %d not found", executorId))
            );


        } else if (releaseId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByAuthorId(authorId);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundException(format("Tasks by this author by ID = %d not found", authorId))
            );

        } else if (authorId == null && executorId == null && status == null) {
            optionalTaskList = taskRepository.findAllByRelease(releaseId);
            taskEntityList = optionalTaskList.orElseThrow(() -> new EntityNotFoundException(
                    format("Tasks by this release by ID = %d not found", releaseId))
            );

        } else {
            optionalTaskList = taskRepository.findAllByReleaseIdAndExecutorIdAndStatus(releaseId, executorId, status);
            taskEntityList = optionalTaskList.orElseThrow(
                    () -> new EntityNotFoundException(
                            format("Tasks by this release by ID = %d executor by ID = %d status = %s not found", releaseId, executorId, status)
                    )
            );
        }

        return taskEntityList.stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public UUID postTask(CreateTaskDto createTaskDto) {
        TaskEntity taskEntity = createMapper.dtoToEntity(createTaskDto);
        TaskEntity taskEntityResult = taskRepository.saveAndFlush(taskEntity);
        return taskEntityResult.getId();
    }

    @Transactional
    @Override
    public void editTask(UUID taskId, EditTaskRequestDto editTaskDto) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format("Task by ID = %s not found", taskId))
        );

        taskEntity.setName(hasText(editTaskDto.getName())
                ? editTaskDto.getName()
                : taskEntity.getName());

        taskEntity.setDescription(hasText(editTaskDto.getDescription())
                ? editTaskDto.getDescription()
                : taskEntity.getDescription());
    }


    @Override
    public void changeExecutor(UUID taskId, ChangeTaskExecutorDto changeTaskExecutorDto) {
        if (changeTaskExecutorDto.getExecutor() == null) {
            throw new EmptyRequestException("Пустой запрос");
        }
        taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format("Task by ID = %s not found", taskId)))
                .setExecutorId(changeTaskExecutorDto.getExecutor());
    }

    @Override
    public void changeStatus(UUID taskId, ChangeTaskStatusDto changeTaskStatusDto) {
        if (changeTaskStatusDto.getStatus() == null) {
            throw new EmptyRequestException("Пустой запрос");
        }
        taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format("Task by ID = %s not found", taskId))).
                setStatus(changeTaskStatusDto.getStatus().toString());
    }

    @Override
    public void changeRelease(UUID taskId, ChangeTaskReleaseDto changeTaskReleaseDto) {
        if (changeTaskReleaseDto.getRelease() == null) {
            throw new EmptyRequestException("Пустой запрос");
        }
        taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format("Task by ID = %s not found", taskId)))
                .setReleaseId(changeTaskReleaseDto.getRelease());
    }

    @Transactional
    @Override
    public void deleteTask(UUID id) {

        taskRepository.delete(taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("Tasks by ID = %s not found", id))));

    }
}

