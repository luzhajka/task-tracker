package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Сервис для работы с пользователями
 */
public interface UserService extends UserDetailsService {

    /**
     * Метод получения пользователя по его ID
     *
     * @param userId - первичный ключ для пользователя
     * @return - User DTO
     */
    UserDto getUser(Long userId);

    /**
     * Метод получения списка всех пользователей
     *
     * @return - список всех пользователей
     */
    List<UserDto> getUsers();

    /**
     * Метод создания пользователя
     *
     * @param userDto DTO пользователя с заполненными полями без ID
     * @return - ID пользователя (первичный ключ)
     */
    Long createUser(CreateUserDto userDto);

    /**
     * Метод изменения пользователя с позможность изменения имени пользователя или роли пользователя
     *
     * @param userId - первичный ключ для пользователя
     * @param editUserRequestDto - DTO пользователя с полями для измениний
     */
    void editUser(Long userId, EditUserRequestDto editUserRequestDto);

    /**
     * Метод удаления пользователя
     *
     * @param userId - первичный ключ для пользователя
     */
    void deleteUser(Long userId);
}
