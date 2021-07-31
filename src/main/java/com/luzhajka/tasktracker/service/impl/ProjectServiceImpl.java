package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundExceptions;
import com.luzhajka.tasktracker.repository.ProjectRepository;
import com.luzhajka.tasktracker.service.ProjectService;
import com.luzhajka.tasktracker.utils.ProjectDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectDtoEntityMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDtoEntityMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public ProjectDto getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .map(mapper::entityToDto)
                .orElseThrow(
                        () -> new EntityNotFoundExceptions(format("Project by id = %s not found", projectId))
                );
    }

    @Transactional
    @Override
    public Long postProject(CreateProjectDto createProjectDTO) {
        return null;
    }

    @Transactional
    @Override
    public void editProject(String projectId, EditProjectRequestDto editProjectRequestDto) {

    }
}
