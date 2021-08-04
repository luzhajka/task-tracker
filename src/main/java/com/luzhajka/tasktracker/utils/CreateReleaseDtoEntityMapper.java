package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CreateReleaseDtoEntityMapper {
    CreateReleaseDto entityToDto (ReleaseEntity entity);
    ReleaseEntity dtoToEntity (CreateReleaseDto dto);

}
