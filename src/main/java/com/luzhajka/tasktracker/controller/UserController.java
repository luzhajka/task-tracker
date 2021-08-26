package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Управление")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "получить пользователя по ID")
    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "получить список пользователей")
    @GetMapping(value = "/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "добавить пользователя")
    @PostMapping(value = "/new")
    public Long createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @Operation(summary = "изменить имя пользователя или роль пользователя")
    @PutMapping(value = "/{id}")
    public void editUser(@PathVariable("id") Long userId,
                         @RequestBody EditUserRequestDto editUserRequestDto) {
        userService.editUser(userId, editUserRequestDto);
    }

    @Operation(summary = "удалить пользователя")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
