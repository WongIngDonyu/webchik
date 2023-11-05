package com.example.webchick.services.impl;

import com.example.webchick.models.User;
import com.example.webchick.repositories.UserRepository;
import com.example.webchick.repositories.UserRoleRepository;
import com.example.webchick.services.UserRoleService;
import com.example.webchick.services.UserService;
import com.example.webchick.services.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID> {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
    }

    @Override
    public void delete(UserDto user) {
        userRepository.deleteById(user.getId());
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map((m)->modelMapper.map(m, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUser(UUID id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDto.class));
    }

    @Override
    public UserDto add(UserDto user) {
        User u = modelMapper.map(user, User.class);
        u.setUserRole(userRoleService.findRoleByName(user.getRole()));
        u.setCreated(LocalDateTime.now());
        return modelMapper.map(userRepository.save(u), UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<User> dbUser = userRepository.findById(userDto.getId());
        if (dbUser.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
            User user1 = dbUser.get();

            user1.setUserRole(userRoleService.findRoleByName(userDto.getRole()));
            user1.setModified(LocalDateTime.now());
            user1.setCreated(dbUser.get().getCreated());
            return modelMapper.map(userRepository.save(user1), UserDto.class);
        }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}

