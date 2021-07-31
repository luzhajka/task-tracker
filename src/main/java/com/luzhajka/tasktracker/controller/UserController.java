package com.luzhajka.tasktracker.controller;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Управление")
@RestController("${server.api-base-url}")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "получить пользователя по ID")
    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "получить список пользователей")
    @GetMapping(value = "/user/role")
    public List<UserDto> getUsers() {
        List<UserDto> userList = List.of();
        return userList;
    }

    @Operation(summary = "добавить пользователя")
    @PostMapping(value = "/user")
    public Long postUser(@RequestBody UserDto userDto) {
        //createUserDTO в БД
        //БД присвоит userID
        Long userId = 33L;
        return userId;

    }

    @Operation(summary = "изменить имя пользователя или роль пользователя")
    @PutMapping(value = "/user/{id}")
    public void editUser(@PathVariable("id") Long userId,
                         @RequestBody EditUserRequestDto editUserRequestDto) {
    }

    @Operation(summary = "удалить пользователя")
    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        // проверить роль запрашивающего на право удаления пользователя
        // удаление сущности из БД
    }
}
