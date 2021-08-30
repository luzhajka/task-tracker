package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateUserDto;
import com.luzhajka.tasktracker.controller.dto.EditUserRequestDto;
import com.luzhajka.tasktracker.controller.dto.UserDto;
import com.luzhajka.tasktracker.entity.UserEntity;
import com.luzhajka.tasktracker.exceptions.UserNotFoundException;
import com.luzhajka.tasktracker.repository.UserRepository;
import com.luzhajka.tasktracker.service.UserService;
import com.luzhajka.tasktracker.utils.CreateUserDtoEntityMapper;
import com.luzhajka.tasktracker.utils.UserDtoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.luzhajka.tasktracker.utils.MessagesUtil.getMessageForLocale;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserDtoEntityMapper mapper;
    private final CreateUserDtoEntityMapper createMapper;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserDtoEntityMapper mapper,
                           CreateUserDtoEntityMapper createMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.createMapper = createMapper;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(
                                format(getMessageForLocale("user.by.id.not.found"), userId)
                        ));
    }

    @Transactional
    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long createUser(CreateUserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        UserEntity userEntity = createMapper.dtoToEntity(userDto);
        UserEntity userEntityResult = userRepository.saveAndFlush(userEntity);
        return userEntityResult.getId();
    }

    @Transactional
    @Override
    public void editUser(Long userId, EditUserRequestDto editUserRequestDto) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("user.by.id.not.found"), userId))
        );

        userEntity.setName(StringUtils.hasText(editUserRequestDto.getUserName())
                ? editUserRequestDto.getUserName()
                : userEntity.getName());

        userEntity.setRole(StringUtils.hasText(editUserRequestDto.getUserRole())
                ? editUserRequestDto.getUserRole()
                : userEntity.getRole());

        userRepository.saveAndFlush(userEntity);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(
                userRepository.findById(userId).orElseThrow(
                        () -> new EntityNotFoundException(format(getMessageForLocale("user.by.id.not.found"), userId))
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findFirstByLogin(login).orElseThrow(
                () -> new UserNotFoundException(format(getMessageForLocale("user.by.login.not.found"), login))
        );
        return userEntity;
    }
}
