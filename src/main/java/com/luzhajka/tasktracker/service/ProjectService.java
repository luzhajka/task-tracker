package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;

public interface ProjectService {

    public ProjectDto getProject(Long projectId);

    public Long createProject(CreateProjectDto createProjectDTO);

    public void editProject(Long projectId, EditProjectRequestDto editProjectRequestDto);

    public void completeProject(Long projectId);

}
