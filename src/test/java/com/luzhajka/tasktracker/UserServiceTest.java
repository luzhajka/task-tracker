package com.luzhajka.tasktracker;


import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.PermissionRole;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.controller.dto.UserRole;
import com.luzhajka.tasktracker.entity.UserEntity;
import com.luzhajka.tasktracker.repository.UserRepository;
import com.luzhajka.tasktracker.service.UserService;
import com.luzhajka.tasktracker.service.impl.UserServiceImpl;
import com.luzhajka.tasktracker.utils.CreateUserDtoEntityMapper;
import com.luzhajka.tasktracker.utils.CreateUserDtoEntityMapperImpl;
import com.luzhajka.tasktracker.utils.UserDtoEntityMapper;
import com.luzhajka.tasktracker.utils.UserDtoEntityMapperImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.luzhajka.tasktracker.TestObjectMother.getRegularUserEntity;
import static com.luzhajka.tasktracker.TestObjectMother.userId;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService UserService(UserRepository userRepository, UserDtoEntityMapper mapper,
                                       CreateUserDtoEntityMapper createMapper, BCryptPasswordEncoder encoder) {
            return new UserServiceImpl(userRepository, mapper, createMapper, encoder);
        }

        @Bean
        public UserDtoEntityMapper mapper() {
            return new UserDtoEntityMapperImpl();
        }

        @Bean
        public CreateUserDtoEntityMapper createMapper() {
            return new CreateUserDtoEntityMapperImpl();
        }
    }

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    BCryptPasswordEncoder encoder;

    @BeforeClass
    public static void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void successGetUserByIdTest() {
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(getRegularUserEntity(userId)));

        UserDto userDto = userService.getUser(userId);

        assertEquals(userId, userDto.getId());
        assertEquals("Ivan", userDto.getName());
        assertEquals("client", userDto.getRole().name());
    }

    @Test
    public void userNotFoundByIdTest() {
        when(userRepository.findById(userId)).thenReturn(empty());
        Exception e = Assertions.assertThrows(Exception.class, () -> userService.getUser(userId));
        Assertions.assertEquals(String.format("User by ID = %d not found", userId), e.getMessage());
    }

    @Test
    public void getUsersTest() {
        when(userRepository.findAll())
                .thenReturn(List.of(getRegularUserEntity(userId), getRegularUserEntity(userId + 1)));
        List<UserDto> users = userService.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(2, users.size());
    }

    @Test
    public void emptyGetUsersTest() {
        when(userRepository.findAll())
                .thenReturn(List.of());
        List<UserDto> users = userService.getUsers();
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @DirtiesContext
    @Test
    public void createUserTest() {

        CreateUserDto createUserDto = new CreateUserDto(
                "Ivan", UserRole.valueOf("client"), "user1", "123456", PermissionRole.valueOf("USER")
        );
        when(encoder.encode(createUserDto.getPassword())).thenReturn("encodePassword");

        UserEntity regularUserEntity = getRegularUserEntity(userId);
        regularUserEntity.setPassword("encodePassword");

        when(userRepository.saveAndFlush(any(UserEntity.class)))
                .thenReturn(regularUserEntity);

        Long userId = userService.createUser(createUserDto);

        assertNotNull(userId);
    }


    @Test
    public void editUserTest() {
        EditUserRequestDto editUserRequestDto = new EditUserRequestDto("userName", "developer");
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(getRegularUserEntity(userId)));

        userService.editUser(userId, editUserRequestDto);

        UserEntity regularUserEntity = getRegularUserEntity(userId);
        regularUserEntity.setName("userName");
        regularUserEntity.setRole("developer");

        Mockito.verify(userRepository, times(1)).saveAndFlush(regularUserEntity);
    }

    @Test
    public void deleteUserTest() {
        UserEntity regularUserEntity = getRegularUserEntity(userId);
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(regularUserEntity));
        userService.deleteUser(userId);
        Mockito.verify(userRepository, times(1)).delete(regularUserEntity);
    }

    @Test
    public void loadUserByUsernameTest() {
        String login = "user1";
        when(userRepository.findFirstByLogin(login)).thenReturn(of(getRegularUserEntity(userId)));

        UserDetails userDetails = userService.loadUserByUsername(login);
        assertNotNull(userDetails);
    }

}
