package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.repository.UserRepository;
import com.luzhajka.tasktracker.service.UserService;
import com.luzhajka.tasktracker.utils.UserDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoEntityMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserDtoEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .map(mapper::entityToDto)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("User by ID = %s not found", userId))
                );
    }

    @Transactional
    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Transactional
    @Override
    public Long postUser(UserDto userDTO) {
        return null;
    }

    @Transactional
    @Override
    public void editUser(Long userID, EditUserRequestDto editUserRequestDto) {

    }
}
