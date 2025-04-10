package com.devops.demo.service.admin;

import com.devops.demo.database.entity.project.UserEntity;

public interface AdminService {
    void createAdminUser(String username, String email, String mobileNumber, String password);
    void deleteUser(Long userId);
    Iterable<UserEntity> getAllUsers();
    void changeUserRole(Long userId, String role);
}