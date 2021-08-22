package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.client.ClientAccountServiceClient;
import com.luzhajka.tasktracker.client.dto.PaymentDto;
import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.controller.dto.ProjectStatus;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.ProjectEntity;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.exceptions.InvalidProjectStateException;
import com.luzhajka.tasktracker.exceptions.PaymentNotFoundException;
import com.luzhajka.tasktracker.repository.ProjectRepository;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.ProjectService;
import com.luzhajka.tasktracker.utils.CreateProjectDtoEntityMapper;
import com.luzhajka.tasktracker.utils.ProjectDtoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;
    private final ProjectDtoEntityMapper mapper;
    private final CreateProjectDtoEntityMapper createMapper;
    private final TaskRepository taskRepository;
    private final ClientAccountServiceClient client;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDtoEntityMapper mapper, CreateProjectDtoEntityMapper createMapper, TaskRepository taskRepository, ClientAccountServiceClient client) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        this.createMapper = createMapper;
        this.taskRepository = taskRepository;
        this.client = client;
    }

    @Transactional
    @Override
    public ProjectDto getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .map(mapper::entityToDto)
                .orElseThrow(
                        () -> {
                            LOGGER.error(format("Project by ID = %d not found", projectId));
                            return new EntityNotFoundException("Project not found");
                        }
                );
    }

    @Transactional
    @Override
    public Long createProject(CreateProjectDto createProjectDTO) {
        ProjectEntity projectEntity = createMapper.dtoToEntity(createProjectDTO);
        ProjectEntity projectEntityResult = projectRepository.saveAndFlush(projectEntity);
        return projectEntityResult.getId();
    }

    @Transactional
    @Override
    public void editProject(Long projectId, EditProjectRequestDto editProjectRequestDto) {
        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> {
                    LOGGER.error(format("Project by ID = %d not found", projectId));
                    return new EntityNotFoundException("Project not found");
                }
        );

        projectEntity.setProjectName(hasText(editProjectRequestDto.getProjectName())
                ? editProjectRequestDto.getProjectName()
                : projectEntity.getProjectName());

        projectEntity.setClientId(editProjectRequestDto.getClientId() != null
                ? editProjectRequestDto.getClientId()
                : projectEntity.getClientId());

        projectEntity.setStatus(hasText(editProjectRequestDto.getStatusProject())
                ? editProjectRequestDto.getStatusProject()
                : projectEntity.getStatus());

        projectRepository.saveAndFlush(projectEntity);

    }

    @Override
    public void completeProject(Long projectId) {
        List<TaskEntity> openedTasks = taskRepository.findAllByProjectIdAndStatusIsNot(projectId, TaskStatus.done.name());
        if (openedTasks.isEmpty()) {
            ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                    () -> {
                        LOGGER.error(format("Project by ID = %d not found", projectId));
                        return new EntityNotFoundException("Project not found");
                    }
            );
            projectEntity.setStatus(ProjectStatus.DONE.name());
            return;
        }
        throw new InvalidProjectStateException("На проекте остались незавершенные задачи");
    }

    @Override
    public void startProject(Long projectId) {
        List<PaymentDto> projectPayment = client.getProjectPayment(projectId);
        if (projectPayment.isEmpty()){
            throw new PaymentNotFoundException(String.format("для проекта %d платежи не найдены", projectId));
        }
        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> {
                    LOGGER.error(format("Project by ID = %d not found", projectId));
                    return new EntityNotFoundException("Project not found");
                }
        );
        projectEntity.setStatus(ProjectStatus.IN_PROGRESS.name());
        projectRepository.saveAndFlush(projectEntity);
    }
}

