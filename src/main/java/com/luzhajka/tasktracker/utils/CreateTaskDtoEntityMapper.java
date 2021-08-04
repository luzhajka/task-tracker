package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CreateTaskDtoEntityMapper {
    CreateTaskDto entityToDto (TaskEntity entity);
    TaskEntity dtoToEntity (CreateTaskDto dto);
}
