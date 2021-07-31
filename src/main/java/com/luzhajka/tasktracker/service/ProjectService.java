package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;

public interface ProjectService {

    public ProjectDto getProject(Long projectId);

    public Long postProject(CreateProjectDto createProjectDTO);

    public void editProject(String projectId, EditProjectRequestDto editProjectRequestDto);
}
