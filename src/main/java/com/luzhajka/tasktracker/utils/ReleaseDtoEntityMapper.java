package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReleaseDtoEntityMapper {
    ReleaseDto entityToDto (ReleaseEntity entity);
    ReleaseEntity dtoToEntity (ReleaseDto dto);
}
