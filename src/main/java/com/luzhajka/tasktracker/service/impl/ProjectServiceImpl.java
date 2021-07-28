package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.entity.ProjectEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundExceptions;
import com.luzhajka.tasktracker.repository.ProjectRepository;
import com.luzhajka.tasktracker.service.ProjectService;
import com.luzhajka.tasktracker.utils.ProjectDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    final ProjectRepository projectRepository;
    final ProjectDtoEntityMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDtoEntityMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public ProjectDto getProject(Long projectId) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()){
            throw new EntityNotFoundExceptions ("Project by id = " + projectId + " not found");
        }
        ProjectEntity projectEntity = projectOptional.get();

        return mapper.entityToDto(projectEntity);
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
