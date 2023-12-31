package com.example.webchick.services;

import com.example.webchick.models.User;
import com.example.webchick.services.dtos.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService <I extends UUID>{
    void delete(UserDto user);

    void delete(UUID id);

    List<UserDto> getAll();

    Optional<UserDto> findUser(UUID id);

    UserDto add(UserDto user);
    UserDto update(UserDto user);
    User findByUsername (String username);
    void activation(UUID id);
}
