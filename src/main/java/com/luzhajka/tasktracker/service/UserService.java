package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto getUser(Long userId);

    public List<UserDto> getUsers();

    public Long postUser(UserDto userDto);

    public void editUser(Long userId, EditUserRequestDto editUserRequestDto);
}
