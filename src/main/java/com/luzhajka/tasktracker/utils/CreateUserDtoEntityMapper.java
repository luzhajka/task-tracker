package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CreateUserDtoEntityMapper {
    CreateUserDto entityToDto (UserEntity entity);
    UserEntity dtoToEntity (CreateUserDto dto);
}
