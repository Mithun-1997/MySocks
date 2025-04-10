package com.devops.demo.database.repository.projectrepository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devops.demo.database.entity.project.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    // ✅ Check if a user exists by username
    boolean existsByUsername(String username);

    // ✅ Check if a user exists by email
    boolean existsByEmail(String email);

    // ✅ Fetch user by username
    Optional<UserEntity> findByUsername(String username);

    // ✅ Fetch all users with specific roles
    List<UserEntity> findByRoleIn(List<Integer> roles);

    // ✅ Update password by user ID
    @Modifying
    @Query("UPDATE UserEntity u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(Long userId, String password);

    // ✅ Update gender by user ID
    @Modifying
    @Query("UPDATE UserEntity u SET u.gender = :gender WHERE u.id = :userId")
    void updateGender(Long userId, Integer gender);

    // ✅ Fix: Removed `.` from `UPDATE.UserEntity`, which was invalid syntax
    @Modifying
    @Query("UPDATE UserEntity u SET u.role = :role WHERE u.id = :userId")
    void updateRole(Long userId, Integer role);
}
