package com.example.webchick.repositories;

import com.example.webchick.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository  extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findByRole(UserRole.Role name);
}
