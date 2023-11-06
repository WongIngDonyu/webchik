package com.example.webchick.services.dtos;

import com.example.webchick.models.UserRole;
import lombok.Getter;

import java.util.UUID;

public class UserDto {
    @Getter
    private UUID id;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    private boolean isActive;
    @Getter
    private String imageUrl;
    @Getter
    private UserRole.Role role;
    public UserDto(){}

    public UserDto(UUID id, String username, String password, String firstName, String lastName, boolean isActive, String imageUrl, UserRole.Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRole(UserRole.Role role) {
        this.role = role;
    }
}