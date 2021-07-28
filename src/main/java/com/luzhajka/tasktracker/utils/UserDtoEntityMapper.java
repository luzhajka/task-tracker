package com.luzhajka.tasktracker.utils;

import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoEntityMapper {

    UserDto entityToDto (UserEntity entity);
    UserEntity dtoToEntity (UserDto dto);
}
