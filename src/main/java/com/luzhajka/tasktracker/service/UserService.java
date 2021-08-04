package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto getUser(Long userId);

    public List<UserDto> getUsers();

    public Long createUser(CreateUserDto userDto);

    public void editUser(Long userId, EditUserRequestDto editUserRequestDto);

    public void deleteUser(Long userId);
}
