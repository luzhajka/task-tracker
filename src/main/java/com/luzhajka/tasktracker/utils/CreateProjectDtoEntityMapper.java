package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CreateProjectDtoEntityMapper {
    CreateProjectDto entityToDto(ProjectEntity entity);

    ProjectEntity dtoToEntity(CreateProjectDto dto);
}
