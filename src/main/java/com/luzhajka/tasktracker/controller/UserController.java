package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.controller.dto.UserDTO;
import com.luzhajka.tasktracker.controller.dto.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Управление пользователями")
@RequestMapping("/api/usersManager")
@RestController
public class UserController {


    @Operation(summary = "получить пользователя по ID")
    @GetMapping(value = "/user/{userID}")
    public UserDTO getUser(@RequestHeader("requester") String requester,
                           @PathVariable("userID") String userID) {
        //заглушка. Вернуть пользователя по userId
        return new UserDTO(122L, "Вася", UserRole.developer);
    }

    @Operation(summary = "получить список пользователей по роли")
    @GetMapping(value = "/user/role")
    public List<UserDTO> getUsers(@RequestHeader("requester") String requester,
                                  @RequestParam(name = "userRole", required = false) String userRole) {
        //заглушка. Фильтруем по параметрам if (параметр != null), если все null - возвращаем userList со всеми пользователями
        List<UserDTO> userList = List.of();
        return userList;
    }

    @Operation(summary = "добавить пользователя")
    @PostMapping(value = "/user")
    public Long postUser(@RequestHeader("requester") String requester,
                         @RequestBody UserDTO userDTO) {
        //createUserDTO в БД
        //БД присвоит userID
        Long userID = 33L;
        return userID;

    }

    @Operation(summary = "изменить имя пользователя или роль пользователя")
    @PutMapping(value = "/user/{userID}")
    public void editUser(@RequestHeader("requester") String requester,
                         @PathVariable("userID") String userID,
                         @RequestParam(name = "userName") String userName,
                         @RequestParam(name = "userRole") String userRole) {
    }

    @Operation(summary = "удалить пользователя")
    @DeleteMapping(value = "/user/{userID}")
    public void deleteUser(@RequestHeader("requester") String requester,
                           @PathVariable("userID") Long id) {
        // проверить роль запрашивающего на право удаления пользователя
        // удаление сущности из БД
    }


}
