package com.devops.demo.service.admin;

import com.devops.demo.database.entity.project.UserEntity;

import com.devops.demo.database.repository.projectrepository.userrepository.UserRepository;
import com.devops.demo.security.encryption.PasswordEncryptionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional("projectTransactionManager")
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncryptionUtil passwordEncryptionUtil;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, PasswordEncryptionUtil passwordEncryptionUtil) {
        this.userRepository = userRepository;
        this.passwordEncryptionUtil = passwordEncryptionUtil;
        System.out.println("✅ AdminServiceImpl created, userRepository = " + userRepository);
    }

    @Override
    public void createAdminUser(String username, String email, String mobileNumber, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity adminUser = new UserEntity();
        adminUser.setUsername(username);
        adminUser.setEmail(email);
        adminUser.setMobileNumber(mobileNumber);
        adminUser.setPassword(passwordEncryptionUtil.encryptPassword(password)); // ✅ Encrypt password
        adminUser.setRole(2); // Admin role = 2
        adminUser.setCreationTime(LocalDateTime.now());
        adminUser.setValidTill(LocalDateTime.now().plusYears(2));

        userRepository.save(adminUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void changeUserRole(Long userId, String role) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            switch (role.toLowerCase()) {
                case "user":
                    user.setRole(1);
                    break;
                case "admin":
                    user.setRole(2);
                    break;
                case "superadmin":
                    user.setRole(4);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role: " + role);
            }
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
