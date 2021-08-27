package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EmptyRequestException;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.exceptions.ReadIncomingFileException;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.TaskService;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapper;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import static com.luzhajka.tasktracker.utils.MessagesUtil.getMessageForLocale;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.csv.CSVFormat.DEFAULT;
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
                        () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), taskId))
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

        if (releaseId == null && authorId == null && executorId == null && status == null) {
            taskEntityList = taskRepository.findAll();
        } else if (releaseId == null && authorId == null && executorId == null) {
            taskEntityList = taskRepository.findAllByStatus(status);
        } else if (releaseId == null && authorId == null && status == null) {
            taskEntityList = taskRepository.findAllByExecutorId(executorId);
        } else if (releaseId == null && executorId == null && status == null) {
            taskEntityList = taskRepository.findAllByAuthorId(authorId);
        } else if (authorId == null && executorId == null && status == null) {
            taskEntityList = taskRepository.findAllByReleaseId(releaseId);
        } else {
            taskEntityList = taskRepository.findAllByReleaseIdAndExecutorIdAndStatus(releaseId, executorId, status);
        }

        return taskEntityList.stream().map(mapper::entityToDto).collect(toList());
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
                () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), taskId))
        );

        taskEntity.setName(hasText(editTaskDto.getName())
                ? editTaskDto.getName()
                : taskEntity.getName());

        taskEntity.setDescription(hasText(editTaskDto.getDescription())
                ? editTaskDto.getDescription()
                : taskEntity.getDescription());

        taskRepository.saveAndFlush(taskEntity);
    }


    @Override
    public void changeExecutor(UUID taskId, ChangeTaskExecutorDto changeTaskExecutorDto) {
        if (changeTaskExecutorDto.getExecutor() == null) {
            throw new EmptyRequestException(getMessageForLocale("empty.request"));
        }
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), taskId)));
        taskEntity.setExecutorId(changeTaskExecutorDto.getExecutor());
        taskRepository.saveAndFlush(taskEntity);
    }

    @Override
    public void changeStatus(UUID taskId, ChangeTaskStatusDto changeTaskStatusDto) {
        if (changeTaskStatusDto.getStatus() == null) {
            throw new EmptyRequestException(getMessageForLocale("empty.request"));
        }
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), taskId)));
        taskEntity.setStatus(changeTaskStatusDto.getStatus().toString());
        taskRepository.saveAndFlush(taskEntity);
    }

    @Override
    public void changeRelease(UUID taskId, ChangeTaskReleaseDto changeTaskReleaseDto) {
        if (changeTaskReleaseDto.getRelease() == null) {
            throw new EmptyRequestException(getMessageForLocale("empty.request"));
        }
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), taskId)));
        taskEntity.setReleaseId(changeTaskReleaseDto.getRelease());
        taskRepository.saveAndFlush(taskEntity);
    }

    @Transactional
    @Override
    public void deleteTask(UUID id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("task.not.found"), id))));
    }

    public void uploadTasks(@RequestParam MultipartFile file) {
        Iterable<CSVRecord> records;
        try {
            records = DEFAULT
                    .withHeader("name", "description", "author", "executor", "status", "release", "project")
                    .parse(new InputStreamReader(file.getInputStream(), UTF_8));
        } catch (Exception e) {
            throw new ReadIncomingFileException(getMessageForLocale("file.not.reading"), e);
        }
        for (CSVRecord record : records) {
            CreateTaskDto createTaskDto = new CreateTaskDto.CreateTaskDTOBuilder()
                    .name("name")
                    .description(record.get("description"))
                    .author(Long.valueOf(record.get("author")))
                    .executor(Long.valueOf(record.get("executor")))
                    .status(TaskStatus.valueOf(record.get("status")))
                    .release(Long.valueOf(record.get("release")))
                    .project(Long.valueOf(record.get("project")))
                    .build();

            postTask(createTaskDto);
        }
    }
}

