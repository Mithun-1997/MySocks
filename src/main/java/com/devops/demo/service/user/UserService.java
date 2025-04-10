package com.devops.demo.service.user;

import com.devops.demo.database.entity.project.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> getUserById(Long id);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    void updatePassword(Long userId, String newPassword);
    void updateGender(Long userId, Integer newGender);
    void updateRole(Long userId, Integer newRole);

    List<UserEntity> getAllUsers();

    void saveUser(UserEntity user);
    Optional<UserEntity> findByUsername(String username);
}
