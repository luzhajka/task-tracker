package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectDtoEntityMapper {
    ProjectDto entityToDto(ProjectEntity entity);

    ProjectEntity dtoToEntity(ProjectDto dto);

}
