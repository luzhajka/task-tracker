package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.entity.UserEntity;
import com.luzhajka.tasktracker.repository.UserRepository;
import com.luzhajka.tasktracker.service.UserService;
import com.luzhajka.tasktracker.utils.UserDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserDtoEntityMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserDtoEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public UserDto getUser(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User by ID = " + userId + " not found");
        }
        UserEntity userEntity = userOptional.get();
        return mapper.entityToDto(userEntity);
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
