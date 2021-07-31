package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TaskDtoEntityMapper {
    TaskDto entityToDto(TaskEntity entity);

    TaskEntity dtoToEntity(TaskDto dto);

}
