package com.example.webchick.services;

import com.example.webchick.models.UserRole;
import com.example.webchick.services.dtos.UserRoleDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService <I extends UUID>{
    void delete(UserRoleDto userRole);

    void delete(UUID id);

    List<UserRoleDto> getAll();

    Optional<UserRoleDto> findUserRole(UUID id);

    UserRoleDto add(UserRoleDto userRole);
    UserRoleDto update(UserRoleDto userRole);
    UserRole findRoleByName(UserRole.Role name);
}
